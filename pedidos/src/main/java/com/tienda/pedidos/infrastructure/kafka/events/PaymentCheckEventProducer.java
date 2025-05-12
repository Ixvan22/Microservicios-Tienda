package com.tienda.pedidos.infrastructure.kafka.events;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PaymentCheckEventProducer {

  private final KafkaTemplate<String, String> kafkaTemplate;

  public void sendPaymentCheck(String event) {
    kafkaTemplate.send("payment-check", event);
  }

}
