package com.tienda.productos.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {

  @NotBlank(message = "El nombre del producto es obligatorio")
  private String name;
  private String description;
  private Integer stock;

}
