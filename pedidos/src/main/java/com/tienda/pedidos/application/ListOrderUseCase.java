package com.tienda.pedidos.application;

import com.tienda.pedidos.domain.models.Order;
import com.tienda.pedidos.domain.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class ListOrderUseCase {

  private final OrderRepository repository;

  public Order list(UUID orderId) {
    return repository.list(orderId);
  }

  public List<Order> listAll() {
    return repository.listAll();
  }

}
