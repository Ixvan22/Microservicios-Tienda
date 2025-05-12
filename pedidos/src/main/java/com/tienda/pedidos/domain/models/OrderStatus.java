package com.tienda.pedidos.domain.models;

public enum OrderStatus {
  PENDING_STOCK,
  PAYMENT_PENDING,
  CONFIRMED,
  CANCELLED,
  FAILED
}
