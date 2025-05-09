package com.tienda.pedidos.infrastructure.repositories;

import com.tienda.pedidos.infrastructure.repositories.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRespositoryJpa extends JpaRepository<OrderEntity, UUID> {
}
