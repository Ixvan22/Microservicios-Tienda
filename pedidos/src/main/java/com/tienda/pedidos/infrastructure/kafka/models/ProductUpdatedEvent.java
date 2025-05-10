package com.tienda.pedidos.infrastructure.kafka.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdatedEvent {

  private UUID productId;
  private BigDecimal price;
  private LocalDateTime updatedAt;

}
