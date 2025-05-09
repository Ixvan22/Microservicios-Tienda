package com.tienda.productos.infrastructure.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {

  @NotBlank(message = "El nombre del producto es obligatorio")
  private String name;

  @NotNull(message = "El precio es obligatorio")
  @DecimalMin(value = "0.01", inclusive = true, message = "El precio debe ser mayor que cero")
  private BigDecimal price;
  private String description;
  private Integer stock;

}
