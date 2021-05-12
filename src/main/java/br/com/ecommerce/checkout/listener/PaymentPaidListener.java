package br.com.ecommerce.checkout.listener;

import br.com.ecommerce.checkout.entity.enumeration.Status;
import br.com.ecommerce.checkout.service.CheckoutService;
import br.com.ecommerce.checkout.streaming.PaymentPaidSink;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import payment.event.PaymentCreatedEvent;

@Component
@RequiredArgsConstructor
public class PaymentPaidListener {

    private final CheckoutService checkoutService;

    @StreamListener(PaymentPaidSink.INPUT)
    public void handler(PaymentCreatedEvent paymentCreatedEvent) {
        checkoutService.updateStatus(paymentCreatedEvent.getCheckoutCode().toString(), Status.APPROVED);
    }
}
