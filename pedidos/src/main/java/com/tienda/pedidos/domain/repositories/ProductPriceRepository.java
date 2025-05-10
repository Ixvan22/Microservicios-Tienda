package com.tienda.pedidos.domain.repositories;

import java.math.BigDecimal;
import java.util.UUID;

public interface ProductPriceRepository {

  BigDecimal getPriceByProductId(UUID productId);

}
