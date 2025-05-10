package com.tienda.productos.application;

import com.tienda.productos.domain.models.Product;
import com.tienda.productos.domain.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UpdateProductUseCase {

  private final ProductRepository repository;

  public Product execute(Product product) throws Exception {
    return repository.update(product);
  }


}
