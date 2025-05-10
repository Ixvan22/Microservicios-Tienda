package com.tienda.productos.infrastructure.kafka.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservedStockEvent {

  private UUID orderId;
  private List<OrderItem> items;

}
