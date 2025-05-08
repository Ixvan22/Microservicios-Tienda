package com.tienda.productos.infrastructure.repositories;

import com.tienda.productos.domain.models.Product;
import com.tienda.productos.domain.repositories.ProductRepository;
import com.tienda.productos.infrastructure.mappers.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ProductRepositoryImpl implements ProductRepository {

  private final ProductRepositoryJpa repository;
  private final ProductMapper mapper;

  @Transactional
  @Override
  public Product create(Product product) {
    return mapper.toDomain(repository.save(mapper.toEntity(product)));
  }

  @Transactional(readOnly = true)
  @Override
  public List<Product> listAll() {
    return mapper.toDomain(repository.findAll());
  }

  @Transactional(readOnly = true)
  @Override
  public Product listProduct(String name) {
    return mapper.toDomain(repository.findByName(name));
  }

}
