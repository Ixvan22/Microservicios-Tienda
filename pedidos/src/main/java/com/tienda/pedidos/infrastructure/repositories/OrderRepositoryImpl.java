package com.tienda.pedidos.infrastructure.repositories;

import com.tienda.pedidos.domain.models.Order;
import com.tienda.pedidos.domain.repositories.OrderRepository;
import com.tienda.pedidos.infrastructure.kafka.events.ReservedStockEventProducer;
import com.tienda.pedidos.infrastructure.kafka.models.ReservedStockEvent;
import com.tienda.pedidos.infrastructure.mappers.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class OrderRepositoryImpl implements OrderRepository {

  private final OrderRepositoryJpa respository;
  private final OrderMapper mapper;
  private final ReservedStockEventProducer reservedStockEventProducer;

  @Override
  public Order create(Order order) {
    Order orderDb = mapper.toDomain(respository.save(mapper.toEntity(order)));

    if (orderDb == null) {
      throw new RuntimeException("Error al crear el pedido");
    }

    ReservedStockEvent reservedStockEvent = new ReservedStockEvent(orderDb.getOrderId(), order.getItems());
    reservedStockEventProducer.sendReservedStock(reservedStockEvent);

    return orderDb;
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
