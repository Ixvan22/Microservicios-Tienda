package com.tienda.productos.domain.repositories;

import com.tienda.productos.domain.models.Product;

public interface ProductRepository {

  Product create(Product product);

}
