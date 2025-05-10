package com.tienda.pedidos.application;

import com.tienda.pedidos.domain.models.Order;
import com.tienda.pedidos.domain.models.OrderItem;
import com.tienda.pedidos.domain.models.OrderStatus;
import com.tienda.pedidos.domain.repositories.OrderRepository;
import com.tienda.pedidos.domain.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class CreateOrderUseCase {

  private final OrderRepository repository;
  private final ProductRepository productRepository;

  public Order execute(List<OrderItem> products) {
    BigDecimal total = BigDecimal.ZERO;

    for (OrderItem item: products) {
      BigDecimal price = productRepository.getPriceByProductId(item.getProductId());
      if (price == null) {
        throw new RuntimeException("Error. Producto no encontrado");
      }
      BigDecimal itemTotal = price.multiply(BigDecimal.valueOf(item.getQuantity()));

      total = total.add(itemTotal);
    }

    Order order = new Order(null, products, total, OrderStatus.PENDING, LocalDateTime.now());

    return repository.create(order);
  }

}
