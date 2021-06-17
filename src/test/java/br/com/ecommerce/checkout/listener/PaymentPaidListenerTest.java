package br.com.ecommerce.checkout.listener;

import br.com.ecommerce.checkout.entity.enumeration.Status;
import br.com.ecommerce.checkout.service.CheckoutService;
import org.junit.jupiter.api.Test;
import payment.event.PaymentCreatedEvent;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PaymentPaidListenerTest {

    private final CheckoutService checkoutServiceMock = mock(CheckoutService.class);
    private final PaymentPaidListener paymentPaidListener = new PaymentPaidListener(checkoutServiceMock);

    @Test
    public void mustExecuteHandlerWithSuccess() {
        final PaymentCreatedEvent paymentCreatedEvent = new PaymentCreatedEvent("AAAA", "BBBB");

        paymentPaidListener.handler(paymentCreatedEvent);

        verify(checkoutServiceMock).updateStatus(paymentCreatedEvent.getCheckoutCode().toString(), Status.APPROVED);
    }
}