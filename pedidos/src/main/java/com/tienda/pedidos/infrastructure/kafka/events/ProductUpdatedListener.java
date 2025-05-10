package com.tienda.pedidos.infrastructure.kafka.events;

import com.tienda.pedidos.domain.repositories.ProductRepository;
import com.tienda.pedidos.infrastructure.kafka.models.ProductUpdatedEvent;
import com.tienda.pedidos.infrastructure.mappers.ProductMapper;
import com.tienda.pedidos.infrastructure.repositories.ProductPriceRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductUpdatedListener {

  private final ProductPriceRepositoryJpa repository;
  private final ProductMapper mapper;

  @KafkaListener(
          topics = "product-updated",
          groupId = "order-service",
          containerFactory = "kafkaListenerContainerFactory"
  )
  public void listen(ProductUpdatedEvent event) {
    System.out.println("EVENT: " + event);
    repository.save(mapper.toEntity(event));
  }

}
