package com.tienda.pedidos.infrastructure.kafka;

import com.tienda.pedidos.infrastructure.kafka.models.ProductUpdatedEvent;
import com.tienda.pedidos.infrastructure.kafka.models.ReservedStockStatusEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrapAddress;

  @Value("${spring.kafka.consumer.group-id}")
  private String groupId;

  // --- Common config
  private Map<String, Object> baseProps() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
    props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
    props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
    props.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);
    return props;
  }

  // --- ProductUpdatedEvent config
  @Bean
  public ConsumerFactory<String, ProductUpdatedEvent> productUpdatedConsumerFactory() {
    Map<String, Object> props = baseProps();
    props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
    props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, ProductUpdatedEvent.class.getName());
    return new DefaultKafkaConsumerFactory<>(props);
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, ProductUpdatedEvent> productUpdatedEventConcurrentKafkaListenerContainerFactory(
          ConsumerFactory<String, ProductUpdatedEvent> consumerFactory) {
    ConcurrentKafkaListenerContainerFactory<String, ProductUpdatedEvent> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory);
    return factory;
  }

  // --- ReservedStockStatusEvent config
  @Bean
  public ConsumerFactory<String, ReservedStockStatusEvent> reservedStockStatusEventConsumerFactory() {
    Map<String, Object> props = baseProps();
    props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
    props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, ReservedStockStatusEvent.class.getName());
    return new DefaultKafkaConsumerFactory<>(props);
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, ReservedStockStatusEvent> reservedStockStatusEventConcurrentKafkaListenerContainerFactory(
          ConsumerFactory<String, ReservedStockStatusEvent> consumerFactory) {
    ConcurrentKafkaListenerContainerFactory<String, ReservedStockStatusEvent> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory);
    return factory;
  }

}
