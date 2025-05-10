package com.tienda.pedidos.infrastructure.repositories.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "products_prices")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPriceEntity {

  @Id
  private UUID productId;
  private BigDecimal price;
  private LocalDateTime updatedAt;

}
