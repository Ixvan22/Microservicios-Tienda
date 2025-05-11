package com.tienda.productos.infrastructure.kafka.events;

import com.tienda.productos.infrastructure.dto.StockDto;
import com.tienda.productos.infrastructure.kafka.models.OrderItem;
import com.tienda.productos.infrastructure.kafka.models.ReservedStockEvent;
import com.tienda.productos.infrastructure.kafka.models.ReservedStockStatusEvent;
import com.tienda.productos.infrastructure.repositories.ProductRepositoryJpa;
import com.tienda.productos.infrastructure.repositories.entities.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class ReservedStockListener {

  private final ProductRepositoryJpa repository;
  private final ReservedStockStatusEventProducer reservedStockStatusEventProducer;

  @Transactional
  @KafkaListener(
          topics = "reserved-stock",
          groupId = "order-service",
          containerFactory = "kafkaListenerContainerFactory"
  )
  public void listen(ReservedStockEvent event) {

    boolean sufficientStock = true;

    for (OrderItem product : event.getItems()) {
      StockDto stock = repository.findStockByProductId(product.getProductId());

      int stockAvailable = stock.getStock() - stock.getReservedStock();
      if (stockAvailable < product.getQuantity()) {
        sufficientStock = false;
      } else {
        ProductEntity productDb = repository.findById(product.getProductId()).orElseThrow();
        productDb.setStock(productDb.getStock() - product.getQuantity());
        productDb.setReservedStock(productDb.getReservedStock() + product.getQuantity());
        repository.save(productDb);
      }
    }

    if (!sufficientStock) {
      reservedStockStatusEventProducer.sendStatusReservedStock(new ReservedStockStatusEvent(event.getOrderId(), false));
      throw new RuntimeException("No hay stock disponible");
    }
    reservedStockStatusEventProducer.sendStatusReservedStock(new ReservedStockStatusEvent(event.getOrderId(), true));

  }

}
