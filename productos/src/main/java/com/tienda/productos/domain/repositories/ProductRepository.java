package com.tienda.productos.domain.repositories;

import com.tienda.productos.domain.models.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ProductRepository {

  Product create(Product product) throws Exception;
  Product update(Product product) throws Exception;
  List<Product> listAll();
  Product listProduct(String name);
  BigDecimal listPriceById(UUID productId);

}
