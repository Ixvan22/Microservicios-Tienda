package com.tienda.pedidos.infrastructure.mappers;

import com.tienda.pedidos.infrastructure.kafka.models.ProductUpdatedEvent;
import com.tienda.pedidos.infrastructure.repositories.entities.ProductPriceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  ProductPriceEntity toEntity(ProductUpdatedEvent event);

}
