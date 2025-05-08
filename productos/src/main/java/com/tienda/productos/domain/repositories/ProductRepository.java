package com.tienda.productos.domain.repositories;

import com.tienda.productos.domain.models.Product;

import java.util.List;

public interface ProductRepository {

  Product create(Product product);
  List<Product> listAll();
  Product listProduct(String name);

}
