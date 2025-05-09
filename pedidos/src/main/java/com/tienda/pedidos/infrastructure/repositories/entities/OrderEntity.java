package com.tienda.pedidos.infrastructure.repositories.entities;

import com.tienda.pedidos.domain.models.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pedidos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID orderId;

  @ElementCollection
  @CollectionTable(name = "pedidos_productos", joinColumns = @JoinColumn(name = "order_id"))
  private List<OrderItem> items;

  private BigDecimal total;

  private OrderStatus status;

  private LocalDateTime createdAt;

}
