package com.tienda.productos.infrastructure.kafka.events;

import com.tienda.productos.infrastructure.kafka.models.ProductUpdatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductUpdatedEventProducer {

  private final KafkaTemplate<String, ProductUpdatedEvent> kafkaTemplate;

  public void sendProductUpdated(ProductUpdatedEvent event) {
    kafkaTemplate.send("product-updated", event.getProductId().toString(), event);
  }

}
