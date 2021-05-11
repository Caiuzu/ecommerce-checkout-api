package br.com.ecommerce.checkout.config;

import br.com.ecommerce.checkout.streaming.CheckoutCreatedSource;
import br.com.ecommerce.checkout.streaming.PaymentPaidSink;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(value = { //TO-DO: Refatorar
        CheckoutCreatedSource.class,
        PaymentPaidSink.class
})
public class StreamingConfig {
}
