package com.tienda.productos.infrastructure.mappers;

import com.tienda.productos.domain.models.Product;
import com.tienda.productos.infrastructure.dto.CreateProductRequest;
import com.tienda.productos.infrastructure.dto.ProductDto;
import com.tienda.productos.infrastructure.dto.UpdateProductRequest;
import com.tienda.productos.infrastructure.repositories.entities.ProductEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  ProductEntity toEntity(Product domain);
  Product toDomain(ProductEntity entity);
  Product toDomain(CreateProductRequest request);
  Product toDomain(UpdateProductRequest request);
  ProductDto toDto(Product domain);
  List<Product> toDomain(List<ProductEntity> entities);
  List<ProductDto> toDto(List<Product> domain);

}
