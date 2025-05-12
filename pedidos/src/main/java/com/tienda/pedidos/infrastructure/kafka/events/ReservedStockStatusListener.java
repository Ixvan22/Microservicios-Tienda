package com.tienda.pedidos.infrastructure.kafka.events;

import com.tienda.pedidos.domain.models.OrderStatus;
import com.tienda.pedidos.infrastructure.kafka.models.ReservedStockEvent;
import com.tienda.pedidos.infrastructure.kafka.models.ReservedStockStatusEvent;
import com.tienda.pedidos.infrastructure.mappers.OrderMapper;
import com.tienda.pedidos.infrastructure.repositories.OrderRepositoryJpa;
import com.tienda.pedidos.infrastructure.repositories.entities.OrderEntity;
import com.tienda.pedidos.infrastructure.repositories.entities.OrderItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ReservedStockStatusListener {

  private final OrderRepositoryJpa repository;
  private final OrderMapper mapper;
  private final OrderConfirmedEventProducer orderConfirmedEventProducer;

  @KafkaListener(
          topics = "stock-status",
          groupId = "order-service",
          containerFactory = "reservedStockStatusEventConcurrentKafkaListenerContainerFactory"
  )
  public void listen(ReservedStockStatusEvent event) {
    OrderEntity orderDb = repository.findById(event.getOrderId()).orElseThrow();

    if (event.getStatus()) {
      orderDb.setStatus(OrderStatus.CONFIRMED);

      List<OrderItemEntity> items = repository.findItemsByOrderId(orderDb.getOrderId());

      // se podria añadir un metodo de pago y si falla enviar sendOrderFailed

      orderConfirmedEventProducer.sendOrderConfirmed(new ReservedStockEvent(event.getOrderId(), mapper.toListDomain(items)));
    } else {
      orderDb.setStatus(OrderStatus.CANCELLED);
    }
    repository.save(orderDb);
  }

}
