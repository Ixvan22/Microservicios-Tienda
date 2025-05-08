package com.tienda.productos.infrastructure.controllers;

import com.tienda.productos.application.CreateProductUseCase;
import com.tienda.productos.application.ListProductUseCase;
import com.tienda.productos.domain.models.Product;
import com.tienda.productos.infrastructure.dto.CreateProductRequest;
import com.tienda.productos.infrastructure.dto.ProductDto;
import com.tienda.productos.infrastructure.mappers.ProductMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products/")
@RequiredArgsConstructor
public class ProductController {

  private final CreateProductUseCase createProductUseCase;
  private final ListProductUseCase listProductUseCase;
  private final ProductMapper mapper;

  @PostMapping
  public ProductDto create(@Valid @RequestBody CreateProductRequest productRequest) {
    productRequest.setDescription(productRequest.getDescription() != null ? productRequest.getDescription() : "");
    productRequest.setStock(productRequest.getStock() != null ? productRequest.getStock() : 0);
    Product product = mapper.toDomain(productRequest);

    return mapper.toDto(createProductUseCase.execute(product));
  }

  @GetMapping
  public List<ProductDto> listAll() {
    return mapper.toDto(listProductUseCase.listAll());
  }

  @GetMapping("/{name}/")
  public ProductDto list(@PathVariable String name) {
    return mapper.toDto(listProductUseCase.list(name));
  }

}
