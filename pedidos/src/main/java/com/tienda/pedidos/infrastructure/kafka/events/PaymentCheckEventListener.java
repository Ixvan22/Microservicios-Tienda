package com.tienda.pedidos.infrastructure.kafka.events;

import com.tienda.pedidos.domain.models.OrderStatus;
import com.tienda.pedidos.infrastructure.kafka.models.PaymentCheckEvent;
import com.tienda.pedidos.infrastructure.kafka.models.ReservedStockEvent;
import com.tienda.pedidos.infrastructure.mappers.OrderMapper;
import com.tienda.pedidos.infrastructure.repositories.OrderRepositoryJpa;
import com.tienda.pedidos.infrastructure.repositories.entities.OrderEntity;
import com.tienda.pedidos.infrastructure.repositories.entities.OrderItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class PaymentCheckEventListener {

  private final OrderRepositoryJpa repository;
  private final OrderMapper mapper;

  private final OrderConfirmedEventProducer orderConfirmedEventProducer;

  @KafkaListener(
          topics = "payment-successful",
          groupId = "order-service"
  )
  public void paymentSuccessful(String orderId) {
    String cleanOrderId = orderId.replace("\"", "");

    OrderEntity orderDb = repository.findById(UUID.fromString(cleanOrderId)).orElseThrow();
    List<OrderItemEntity> items = repository.findItemsByOrderId(orderDb.getOrderId());

    orderDb.setStatus(OrderStatus.CONFIRMED);
    repository.save(orderDb);

    orderConfirmedEventProducer.sendOrderConfirmed(new ReservedStockEvent(orderDb.getOrderId(), mapper.toListDomain(items)));

  }

  @KafkaListener(
          topics = "payment-failed",
          groupId = "order-service"
  )
  public void paymentFailed(String orderId) {
    String cleanOrderId = orderId.replace("\"", "");

    OrderEntity orderDb = repository.findById(UUID.fromString(cleanOrderId)).orElseThrow();
    List<OrderItemEntity> items = repository.findItemsByOrderId(orderDb.getOrderId());

    orderDb.setStatus(OrderStatus.FAILED);
    repository.save(orderDb);

    orderConfirmedEventProducer.sendOrderFailed(new ReservedStockEvent(orderDb.getOrderId(), mapper.toListDomain(items)));

  }

}
