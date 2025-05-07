package com.tienda.productos.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

  private UUID productId;
  private String name;
  private String description;
  private int stock;

}
