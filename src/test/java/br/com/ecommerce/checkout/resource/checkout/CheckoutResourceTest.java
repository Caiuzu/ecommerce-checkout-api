package br.com.ecommerce.checkout.resource.checkout;

import br.com.ecommerce.checkout.factory.CheckoutRequestFactory;
import br.com.ecommerce.checkout.service.CheckoutService;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CheckoutResourceTest {

    private final CheckoutService checkoutServiceMock = mock(CheckoutService.class);
    private final CheckoutResource checkoutResource = new CheckoutResource(checkoutServiceMock);
    private final CheckoutRequestFactory checkoutRequestFactory = new CheckoutRequestFactory();

    @Test
    void mustCreateCheckoutResource() {
        final CheckoutRequest checkoutRequest = checkoutRequestFactory.createCheckoutRequestFactory();

        checkoutResource.create(checkoutRequest);

        verify(checkoutServiceMock).create(checkoutRequestFactory.createCheckoutRequestFactory());
    }
}