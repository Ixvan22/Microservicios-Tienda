package com.tienda.productos.infrastructure.kafka.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

  private UUID productId;
  private int quantity;

}

