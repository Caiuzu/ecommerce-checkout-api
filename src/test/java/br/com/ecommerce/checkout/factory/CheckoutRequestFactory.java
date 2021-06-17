package br.com.ecommerce.checkout.factory;

import br.com.ecommerce.checkout.resource.checkout.CheckoutRequest;

import java.util.ArrayList;
import java.util.List;

public class CheckoutRequestFactory {

    private static final String FIRST_NAME = "Bertrano";
    private static final String LAST_NAME = "De Tal";
    private static final String EMAIL = "fulano@email.com";
    private static final String ADDRESS = "Cafundo dos Testes";
    private static final String COMPLEMENT = "155";
    private static final String COUNTRY = "Teste";
    private static final String STATE = "CREATED";
    private static final String CEP = "123456789";
    private static final Boolean SAVE_ADDRESS = true;
    private static final Boolean SAVE_INFO = true;
    private static final String PAYMENT_METHOD = "";
    private static final String CARD_NAME = "São Teste";
    private static final String CARD_NUMBER = "0000-0000-0000-0000";
    private static final String CARD_DATE = "0101";
    private static final String CARD_CVV = "000";
    private static final List<String> PPRODUCTS = new ArrayList<>();

    public CheckoutRequest createCheckoutRequestFactory(){
       return new CheckoutRequest(FIRST_NAME, LAST_NAME, EMAIL,
               ADDRESS, COMPLEMENT, COUNTRY, STATE, CEP,
               SAVE_ADDRESS, SAVE_INFO, PAYMENT_METHOD, CARD_NAME, CARD_NUMBER, CARD_DATE, CARD_CVV, PPRODUCTS) ;
    }
}