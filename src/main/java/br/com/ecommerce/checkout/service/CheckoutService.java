package br.com.ecommerce.checkout.service;

import br.com.ecommerce.checkout.entity.CheckoutEntity;
import br.com.ecommerce.checkout.entity.CheckoutItemEntity;
import br.com.ecommerce.checkout.entity.ShippingEntity;
import br.com.ecommerce.checkout.entity.enumeration.Status;
import br.com.ecommerce.checkout.repository.CheckoutRepository;
import br.com.ecommerce.checkout.resource.checkout.CheckoutRequest;
import br.com.ecommerce.checkout.streaming.CheckoutCreatedSource;
import checkout.event.CheckoutCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CheckoutService {

    private final CheckoutRepository checkoutRepository;
    private final CheckoutCreatedSource checkoutCreatedSource;

    public Optional<CheckoutEntity> create(CheckoutRequest checkoutRequest) {
        final CheckoutEntity checkoutEntity = getCheckoutEntity(checkoutRequest);

        setCheckoutRequestItems(checkoutRequest, checkoutEntity);

        final CheckoutEntity entity = checkoutRepository.save(checkoutEntity);
        final CheckoutCreatedEvent checkoutCreatedEvent = getCheckoutCreatedEvent(entity);

        checkoutCreatedSource.output().send(MessageBuilder.withPayload(checkoutCreatedEvent).build());

        return Optional.of(entity);
    }

    private CheckoutEntity getCheckoutEntity(final CheckoutRequest checkoutRequest) {
        return CheckoutEntity.builder()
                .code(UUID.randomUUID().toString())
                .status(Status.CREATED)
                .saveAddress(checkoutRequest.getSaveAddress())
                .saveInformation(checkoutRequest.getSaveInfo())
                .shipping(ShippingEntity.builder()
                        .address(checkoutRequest.getAddress())
                        .complement(checkoutRequest.getComplement())
                        .country(checkoutRequest.getCountry())
                        .state(checkoutRequest.getState())
                        .cep(checkoutRequest.getCep())
                        .build())
                .build();
    }

    private void setCheckoutRequestItems(final CheckoutRequest checkoutRequest, final CheckoutEntity checkoutEntity) {
        checkoutEntity.setItems(checkoutRequest.getProducts()
                .stream()
                .map(product -> CheckoutItemEntity.builder()
                        .checkout(checkoutEntity)
                        .product(product)
                        .build())
                .collect(Collectors.toList()));
    }

    private CheckoutCreatedEvent getCheckoutCreatedEvent(final CheckoutEntity entity) {
        return CheckoutCreatedEvent.newBuilder()
                .setCheckoutCode(entity.getCode())
                .setStatus(entity.getStatus().name())
                .build();
    }

    public void updateStatus(String checkoutCode, Status status) {
        final CheckoutEntity checkoutEntity = checkoutRepository.findByCode(checkoutCode)
                .orElse(CheckoutEntity.builder().build());
        checkoutEntity.setStatus(status);
        checkoutRepository.save(checkoutEntity);
    }

}
