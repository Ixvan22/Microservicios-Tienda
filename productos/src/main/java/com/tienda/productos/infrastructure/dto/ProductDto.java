package com.tienda.productos.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

  private UUID productId;
  private String name;
  private BigDecimal price;
  private String description;
  private int stock;

}
