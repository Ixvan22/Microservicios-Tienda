package com.tienda.pedidos.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

  private UUID orderId;
  private List<OrderItem> items;
  private BigDecimal total;
  private OrderStatus status;
  private LocalDateTime createdAt;

}
