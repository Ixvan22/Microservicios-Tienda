package com.tienda.pagos.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProcessPayment {

  public boolean processPayment(String orderId) {
    return Math.random() > 0.5;
  }

}
