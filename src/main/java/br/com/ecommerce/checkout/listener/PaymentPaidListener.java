package br.com.ecommerce.checkout.listener;

import br.com.ecommerce.checkout.entity.enumeration.Status;
import br.com.ecommerce.checkout.service.CheckoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import payment.event.PaymentCreatedEvent;

@Component
@RequiredArgsConstructor
public class PaymentPaidListener {

    private final CheckoutService checkoutService;

    @KafkaListener(topics = "${spring.cloud.stream.bindings.payment-paid-input.destination}",
            groupId = "${spring.cloud.stream.bindings.payment-paid-input.group}")
    public void handler(PaymentCreatedEvent paymentCreatedEvent) {
        checkoutService.updateStatus(paymentCreatedEvent.getCheckoutCode().toString(), Status.APPROVED);
    }
}
