package com.tienda.pedidos.infrastructure.repositories;

import com.tienda.pedidos.domain.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

  private final WebClient.Builder client;

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
}
