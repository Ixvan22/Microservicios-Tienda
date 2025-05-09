package com.tienda.pedidos.infrastructure.repositories.entities;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

  private UUID productId;
  private int quantity;

}
