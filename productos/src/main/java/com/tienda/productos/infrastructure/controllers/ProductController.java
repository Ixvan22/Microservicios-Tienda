package com.tienda.productos.infrastructure.controllers;

import com.tienda.productos.application.CreateProductUseCase;
import com.tienda.productos.application.ListProductUseCase;
import com.tienda.productos.application.UpdateProductUseCase;
import com.tienda.productos.domain.models.Product;
import com.tienda.productos.infrastructure.dto.CreateProductRequest;
import com.tienda.productos.infrastructure.dto.ProductDto;
import com.tienda.productos.infrastructure.dto.UpdateProductRequest;
import com.tienda.productos.infrastructure.mappers.ProductMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products/")
@RequiredArgsConstructor
public class ProductController {

  private final CreateProductUseCase createProductUseCase;
  private final UpdateProductUseCase updateProductUseCase;
  private final ListProductUseCase listProductUseCase;
  private final ProductMapper mapper;

  @PostMapping
  public ResponseEntity<ProductDto> create(@Valid @RequestBody CreateProductRequest productRequest) {
    productRequest.setDescription(productRequest.getDescription() != null ? productRequest.getDescription() : "");
    productRequest.setStock(productRequest.getStock() != null ? productRequest.getStock() : 0);
    Product product = mapper.toDomain(productRequest);

    try {
      return ResponseEntity.ok(mapper.toDto(createProductUseCase.execute(product)));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @PutMapping
  public ResponseEntity<ProductDto> update(@RequestBody UpdateProductRequest productRequest) {
    try {
      return ResponseEntity.ok(mapper.toDto(updateProductUseCase.execute(mapper.toDomain(productRequest))));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping
  public ResponseEntity<List<ProductDto>> listAll() {
    return ResponseEntity.ok(mapper.toDto(listProductUseCase.listAll()));
  }

  @GetMapping("/{name}")
  public ResponseEntity<ProductDto> list(@PathVariable String name) {
    return ResponseEntity.ok(mapper.toDto(listProductUseCase.list(name)));
  }

  @GetMapping("/{id}/price")
  public ResponseEntity<BigDecimal> listPriceById(@PathVariable String id) {
    return ResponseEntity.ok(listProductUseCase.listPrice(UUID.fromString(id)));
  }

}
