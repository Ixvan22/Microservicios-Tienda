package com.tienda.pagos.infrastructure.kafka.events;

import com.tienda.pagos.application.ProcessPayment;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PaymentCheckEventListener {

  private final ProcessPayment processPayment;

  private final ConfirmPaymentEventProducer confirmPaymentEventProducer;

  @KafkaListener(
          topics = "payment-check",
          groupId = "order-service"
  )
  public void listen(String orderId) {
    if (processPayment.processPayment(orderId)) {
      confirmPaymentEventProducer.confirmPayment(orderId);
    } else {
      confirmPaymentEventProducer.failedPayment(orderId);
    }
  }

}
