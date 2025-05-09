package com.tienda.pedidos.infrastructure.controllers;

import com.tienda.pedidos.application.CreateOrderUseCase;
import com.tienda.pedidos.domain.models.OrderItem;
import com.tienda.pedidos.infrastructure.dto.OrderDto;
import com.tienda.pedidos.infrastructure.mappers.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders/")
@RequiredArgsConstructor
public class OrderController {

  private final CreateOrderUseCase createOrderUseCase;

  private final OrderMapper mapper;

  @PostMapping
  public ResponseEntity<OrderDto> create(@RequestBody List<OrderItem> products) {
    return ResponseEntity.ok(mapper.toDto(createOrderUseCase.execute(products)));
  }

}
