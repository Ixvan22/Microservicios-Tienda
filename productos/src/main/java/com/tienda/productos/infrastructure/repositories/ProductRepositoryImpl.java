package com.tienda.productos.infrastructure.repositories;

import com.tienda.productos.domain.models.Product;
import com.tienda.productos.domain.repositories.ProductRepository;
import com.tienda.productos.infrastructure.kafka.events.ProductUpdatedEventProducer;
import com.tienda.productos.infrastructure.kafka.models.ProductUpdatedEvent;
import com.tienda.productos.infrastructure.mappers.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class ProductRepositoryImpl implements ProductRepository {

  private final ProductRepositoryJpa repository;
  private final ProductMapper mapper;
  private final ProductUpdatedEventProducer productUpdatedEventProducer;

  @Transactional
  @Override
  public Product create(Product product) throws Exception {
    Product productDb = mapper.toDomain(repository.save(mapper.toEntity(product)));
    if (productDb == null) {
      throw new Exception("Error al crear el producto");
    }

    ProductUpdatedEvent productUpdatedEvent = new ProductUpdatedEvent(productDb.getProductId(), productDb.getPrice(), LocalDateTime.now());
    productUpdatedEventProducer.sendProductUpdated(productUpdatedEvent);

    return productDb;
  }

  @Override
  public Product update(Product product) throws Exception {

    Product existingProduct = mapper.toDomain(repository.findById(product.getProductId()).orElseThrow());

    boolean sendProductUpdated = false;
    if (!existingProduct.getPrice().equals(product.getPrice())) sendProductUpdated = true;

    existingProduct.setName(product.getName());
    existingProduct.setDescription(product.getDescription());
    existingProduct.setPrice(product.getPrice());
    existingProduct.setStock(product.getStock());


    Product productDb = mapper.toDomain(repository.save(mapper.toEntity(existingProduct)));
    if (productDb == null) {
      throw new Exception("Error al actualizar el producto");
    }

    if (sendProductUpdated) {
      ProductUpdatedEvent productUpdatedEvent = new ProductUpdatedEvent(productDb.getProductId(), productDb.getPrice(), LocalDateTime.now());
      productUpdatedEventProducer.sendProductUpdated(productUpdatedEvent);
    }
    return productDb;
  }

  @Transactional(readOnly = true)
  @Override
  public List<Product> listAll() {
    return mapper.toDomain(repository.findAll());
  }

  @Transactional(readOnly = true)
  @Override
  public Product listProduct(String name) {
    return mapper.toDomain(repository.findByName(name));
  }

  @Transactional(readOnly = true)
  @Override
  public BigDecimal listPriceById(UUID productId) {
    return repository.findPriceByProductId(productId);
  }

}
