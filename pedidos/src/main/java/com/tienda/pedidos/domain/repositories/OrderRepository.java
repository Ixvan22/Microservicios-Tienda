package com.tienda.pedidos.domain.repositories;

import com.tienda.pedidos.domain.models.Order;

import java.util.List;
import java.util.UUID;

public interface OrderRepository {

  Order create(Order order);
  Order list(UUID orderId);
  List<Order> listAll();

}
