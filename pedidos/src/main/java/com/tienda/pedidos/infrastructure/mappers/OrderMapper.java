package com.tienda.pedidos.infrastructure.mappers;

import com.tienda.pedidos.domain.models.Order;
import com.tienda.pedidos.domain.models.OrderItem;
import com.tienda.pedidos.infrastructure.dto.OrderDto;
import com.tienda.pedidos.infrastructure.repositories.entities.OrderEntity;
import com.tienda.pedidos.infrastructure.repositories.entities.OrderItemEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

  OrderEntity toEntity(Order domain);
  Order toDomain(OrderEntity entity);
  List<Order> toDomain(List<OrderEntity> entities);
  OrderDto toDto(Order domain);
  List<OrderItemEntity> toEntities(List<OrderItem> domain);
  List<OrderItem> toListDomain(List<OrderItemEntity> entities);

}
