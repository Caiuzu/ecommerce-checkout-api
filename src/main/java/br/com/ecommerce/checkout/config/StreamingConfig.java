package br.com.ecommerce.checkout.config;

import checkout.event.CheckoutCreatedEvent;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class StreamingConfig {

    @Value("${spring.kafka.properties.schema.registry.url}")
    private String schemaRegistryUrl;

    @Value("${spring.cloud.stream.bindings.checkout-created-output.destination}")
    private String defaultTopic;

    @Bean
    public KafkaTemplate<String, CheckoutCreatedEvent> kafkaTemplate() {
        val kafkaTemplate = new KafkaTemplate<>(producerFactory());
        kafkaTemplate.setDefaultTopic(defaultTopic);
        return kafkaTemplate;
    }

    private ProducerFactory<String, CheckoutCreatedEvent> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);
        return new DefaultKafkaProducerFactory<>(configProps);
    }
}
