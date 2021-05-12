package br.com.ecommerce.checkout.streaming;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface PaymentPaidSink {

    String INPUT = "payment-paid-input";

    @Input(INPUT) //TO-DO: Refatorar
    SubscribableChannel input();
}
