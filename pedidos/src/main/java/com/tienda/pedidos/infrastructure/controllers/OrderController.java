package com.tienda.pedidos.infrastructure.controllers;

import com.tienda.pedidos.application.CreateOrderUseCase;
import com.tienda.pedidos.application.ListOrderUseCase;
import com.tienda.pedidos.domain.models.OrderItem;
import com.tienda.pedidos.infrastructure.dto.OrderDto;
import com.tienda.pedidos.infrastructure.mappers.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders/")
@RequiredArgsConstructor
public class OrderController {

  private final CreateOrderUseCase createOrderUseCase;
  private final ListOrderUseCase listOrderUseCase;

  private final OrderMapper mapper;

  @PostMapping
  public ResponseEntity<OrderDto> create(@RequestBody List<OrderItem> products) {
    return ResponseEntity.ok(mapper.toDto(createOrderUseCase.execute(products)));
  }

  @GetMapping("/{id}")
  public ResponseEntity<OrderDto> list(@PathVariable String id) {
    return ResponseEntity.ok(mapper.toDto(listOrderUseCase.list(UUID.fromString(id))));
  }

  @GetMapping
  public ResponseEntity<List<OrderDto>> listAll() {
    return ResponseEntity.ok(mapper.toDto(listOrderUseCase.listAll()));
  }

}
