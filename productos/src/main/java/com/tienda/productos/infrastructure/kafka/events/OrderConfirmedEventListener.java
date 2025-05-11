package com.tienda.productos.infrastructure.kafka.events;

import com.tienda.productos.application.UpdateProductUseCase;
import com.tienda.productos.infrastructure.kafka.models.OrderItem;
import com.tienda.productos.infrastructure.kafka.models.ReservedStockEvent;
import com.tienda.productos.infrastructure.mappers.ProductMapper;
import com.tienda.productos.infrastructure.repositories.ProductRepositoryJpa;
import com.tienda.productos.infrastructure.repositories.entities.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class OrderConfirmedEventListener {

  private final ProductRepositoryJpa repository;
  private final ProductMapper mapper;
  private final UpdateProductUseCase updateProductUseCase;

  @Transactional
  @KafkaListener(
          topics = "order-confirmed",
          groupId = "order-service",
          containerFactory = "kafkaListenerContainerFactory"
  )
  public void listen(ReservedStockEvent event) throws Exception {
    for (OrderItem item : event.getItems()) {
      ProductEntity productDb = repository.findById(item.getProductId()).orElseThrow();
      productDb.setReservedStock(productDb.getReservedStock() - item.getQuantity());

      updateProductUseCase.execute(mapper.toDomain(productDb));
    }
  }
}
