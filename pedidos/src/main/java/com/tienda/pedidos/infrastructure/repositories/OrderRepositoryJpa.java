package com.tienda.pedidos.infrastructure.repositories;

import com.tienda.pedidos.infrastructure.repositories.entities.OrderEntity;
import com.tienda.pedidos.infrastructure.repositories.entities.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface OrderRepositoryJpa extends JpaRepository<OrderEntity, UUID> {

  @Query("SELECT i FROM OrderEntity o JOIN o.items i WHERE o.orderId = :orderId")
  List<OrderItemEntity> findItemsByOrderId(UUID orderId);

}
