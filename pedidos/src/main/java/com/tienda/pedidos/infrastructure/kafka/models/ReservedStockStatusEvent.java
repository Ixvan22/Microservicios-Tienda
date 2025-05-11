package com.tienda.pedidos.infrastructure.kafka.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservedStockStatusEvent {

  private UUID orderId;
  private Boolean status;

}

