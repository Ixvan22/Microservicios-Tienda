package com.tienda.pedidos.infrastructure.kafka.events;

import com.tienda.pedidos.infrastructure.kafka.models.ReservedStockEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderConfirmedEventProducer {

  private final KafkaTemplate<String, ReservedStockEvent> kafkaTemplate;

  public void sendOrderConfirmed(ReservedStockEvent event) {
    kafkaTemplate.send("order-confirmed", event.getOrderId().toString(), event);
  }

  public void sendOrderFailed(ReservedStockEvent event) {
    kafkaTemplate.send("order-failed", event.getOrderId().toString(), event);
  }

}
