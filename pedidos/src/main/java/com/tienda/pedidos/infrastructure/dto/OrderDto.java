package com.tienda.pedidos.infrastructure.dto;

import com.tienda.pedidos.domain.models.OrderItem;
import com.tienda.pedidos.domain.models.OrderStatus;
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
public class OrderDto {

  private UUID orderId;
  private List<OrderItem> items;
  private BigDecimal total;
  private OrderStatus status;
  private LocalDateTime createdAt;

}
