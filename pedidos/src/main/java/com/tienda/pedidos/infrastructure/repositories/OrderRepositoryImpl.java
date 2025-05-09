package com.tienda.pedidos.infrastructure.repositories;

import com.tienda.pedidos.domain.models.Order;
import com.tienda.pedidos.domain.repositories.OrderRepository;
import com.tienda.pedidos.infrastructure.mappers.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class OrderRepositoryImpl implements OrderRepository {

  private final OrderRespositoryJpa respository;
  private final OrderMapper mapper;

  @Override
  public Order create(Order order) {
    return mapper.toDomain(respository.save(mapper.toEntity(order)));
  }

  @Override
  public Order list(UUID orderId) {
    return mapper.toDomain(respository.findById(orderId).orElseThrow());
  }

  @Override
  public List<Order> listAll() {
    return mapper.toDomain(respository.findAll());
  }
}
