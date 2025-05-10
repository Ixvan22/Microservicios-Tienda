package com.tienda.pedidos.infrastructure.kafka.events;

import com.tienda.pedidos.infrastructure.kafka.models.ReservedStockEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservedStockEventProducer {

  private final KafkaTemplate<String, ReservedStockEvent> kafkaTemplate;

  public void sendReservedStock(ReservedStockEvent event) {
    kafkaTemplate.send("reserved-stock", event.getOrderId().toString(), event);
  }

}
