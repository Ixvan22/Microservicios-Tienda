package com.tienda.productos.infrastructure.repositories;

import com.tienda.productos.infrastructure.repositories.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.UUID;

public interface ProductRepositoryJpa extends JpaRepository<ProductEntity, UUID> {

  ProductEntity findByName(String name);

  @Query("SELECT p.price FROM ProductEntity p WHERE p.productId = :productId")
  BigDecimal findPriceByProductId(UUID productId);


}
