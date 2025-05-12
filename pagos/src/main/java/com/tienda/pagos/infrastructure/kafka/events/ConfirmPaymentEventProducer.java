package com.tienda.pagos.infrastructure.kafka.events;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ConfirmPaymentEventProducer {

  private final KafkaTemplate<String, String> kafkaTemplate;

  public void confirmPayment(String event) {
    kafkaTemplate.send("payment-successful", event);
  }

  public void failedPayment(String event) {
    kafkaTemplate.send("payment-failed", event);
  }

}
