package com.tienda.productos.infrastructure.repositories;

import com.tienda.productos.infrastructure.repositories.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepositoryJpa extends JpaRepository<ProductEntity, UUID> {
}
