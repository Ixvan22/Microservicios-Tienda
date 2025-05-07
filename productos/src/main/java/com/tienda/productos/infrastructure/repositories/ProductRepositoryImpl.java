package com.tienda.productos.infrastructure.repositories;

import com.tienda.productos.domain.models.Product;
import com.tienda.productos.domain.repositories.ProductRepository;
import com.tienda.productos.infrastructure.mappers.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductRepositoryImpl implements ProductRepository {

  private final ProductRepositoryJpa repository;
  private final ProductMapper mapper;

  @Override
  public Product create(Product product) {
    return mapper.toDomain(repository.save(mapper.toEntity(product)));
  }

}
