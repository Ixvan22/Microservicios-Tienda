package com.tienda.pedidos.infrastructure.kafka.events;

import com.tienda.pedidos.domain.models.OrderStatus;
import com.tienda.pedidos.infrastructure.kafka.models.ReservedStockStatusEvent;
import com.tienda.pedidos.infrastructure.mappers.OrderMapper;
import com.tienda.pedidos.infrastructure.repositories.OrderRepositoryJpa;
import com.tienda.pedidos.infrastructure.repositories.entities.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ReservedStockStatusListener {

  private final OrderRepositoryJpa repository;
  private final PaymentCheckEventProducer paymentCheckEventProducer;

  @KafkaListener(
          topics = "stock-status",
          groupId = "order-service",
          containerFactory = "reservedStockStatusEventConcurrentKafkaListenerContainerFactory"
  )
  public void listen(ReservedStockStatusEvent event) {
    OrderEntity orderDb = repository.findById(event.getOrderId()).orElseThrow();

    if (event.getStatus()) {
      orderDb.setStatus(OrderStatus.PAYMENT_PENDING);

      paymentCheckEventProducer.sendPaymentCheck(orderDb.getOrderId().toString());
    } else {
      orderDb.setStatus(OrderStatus.CANCELLED);
    }
    repository.save(orderDb);
  }

}
