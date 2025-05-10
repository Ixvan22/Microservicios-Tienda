package com.tienda.pedidos.infrastructure.kafka.models;

import com.tienda.pedidos.domain.models.OrderItem;
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
