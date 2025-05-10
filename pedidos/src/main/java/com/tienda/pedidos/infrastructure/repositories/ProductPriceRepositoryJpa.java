package com.tienda.pedidos.infrastructure.repositories;

import com.tienda.pedidos.infrastructure.repositories.entities.ProductPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.UUID;

public interface ProductPriceRepositoryJpa extends JpaRepository<ProductPriceEntity, UUID> {

  //@Query("SELECT p.price FROM ProductPriceEntity p WHERE p.productId = :productId")
  BigDecimal findPriceByProductId(UUID productId);

}
