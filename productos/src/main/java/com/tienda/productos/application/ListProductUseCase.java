package com.tienda.productos.application;

import com.tienda.productos.domain.models.Product;
import com.tienda.productos.domain.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class ListProductUseCase {

  private final ProductRepository repository;

  public List<Product> listAll() {
    return repository.listAll();
  }

  public Product list(String name) {
    return repository.listProduct(name);
  }

  public BigDecimal listPrice(UUID productId) {
    return repository.listPriceById(productId);
  }
}
