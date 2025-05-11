package com.tienda.productos.infrastructure.kafka.events;


import com.tienda.productos.infrastructure.kafka.models.ReservedStockStatusEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservedStockStatusEventProducer {

  private final KafkaTemplate<String, ReservedStockStatusEvent> kafkaTemplate;

  public void sendStatusReservedStock(ReservedStockStatusEvent event ) {
    kafkaTemplate.send("stock-status", event.getOrderId().toString(), event);
  }

}
