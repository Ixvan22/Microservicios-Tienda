package com.tienda.pedidos.infrastructure.repositories;

import com.tienda.pedidos.domain.repositories.ProductPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductPriceRepositoryImpl implements ProductPriceRepository {

  private final ProductPriceRepositoryJpa repository;

  @Override
  public BigDecimal getPriceByProductId(UUID productId) {
    return repository.findPriceByProductId(productId);
  }

  // private final WebClient.Builder client;

  /*
  @Override
  public BigDecimal getPriceByProductId(UUID productId) {
    return client.build()
            .get()
            .uri("/{id}/price", productId)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToFlux(BigDecimal.class)
            .blockFirst();
  }
  */
}
