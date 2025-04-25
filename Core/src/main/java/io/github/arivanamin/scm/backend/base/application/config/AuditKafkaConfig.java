package io.github.arivanamin.scm.backend.base.application.config;

import io.github.arivanamin.scm.backend.base.domain.audit.AuditEvent;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AuditKafkaConfig {

    public static final int MESSAGE_TIMEOUT = 1000;
    public static final int BROKER_RECONNECT_TIMEOUT = 5000;

    public static final String KAFKA_STRING_SERIALIZER =
        "org.apache.kafka.common.serialization.StringSerializer";
    public static final String KAFKA_JSON_SERIALIZER =
        "org.springframework.kafka.support.serializer.JsonSerializer";

    public static final String KAFKA_STRING_DESERIALIZER =
        "org.apache.kafka.common.serialization.StringDeserializer";
    public static final String KAFKA_JSON_DESERIALIZER =
        "org.springframework.kafka.support.serializer.JsonDeserializer";

    @Value ("${KAFKA_BROKER_SERVER:localhost}")
    private String brokerServer;

    @Value ("${KAFKA_BROKER_PORT:9092}")
    private String brokerPort;

    @Bean
    public KafkaTemplate<String, AuditEvent> kafkaTemplate () {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ProducerFactory<String, AuditEvent> producerFactory () {
        Map<String, Object> kafkaConfig = new HashMap<>();

        kafkaConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
            "%s:%s".formatted(brokerServer, brokerPort));

        kafkaConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, KAFKA_STRING_SERIALIZER);
        kafkaConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KAFKA_JSON_SERIALIZER);
        kafkaConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, KAFKA_STRING_DESERIALIZER);
        kafkaConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KAFKA_JSON_DESERIALIZER);
        kafkaConfig.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, MESSAGE_TIMEOUT);
        kafkaConfig.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, MESSAGE_TIMEOUT);
        kafkaConfig.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, MESSAGE_TIMEOUT);
        kafkaConfig.put(ProducerConfig.RETRIES_CONFIG, 0);
        kafkaConfig.put(CommonClientConfigs.RECONNECT_BACKOFF_MS_CONFIG, BROKER_RECONNECT_TIMEOUT);
        kafkaConfig.put(CommonClientConfigs.RECONNECT_BACKOFF_MAX_MS_CONFIG,
            BROKER_RECONNECT_TIMEOUT);
        kafkaConfig.put(ProducerConfig.METADATA_MAX_AGE_CONFIG, MESSAGE_TIMEOUT);
        kafkaConfig.put(ProducerConfig.METADATA_MAX_IDLE_CONFIG, BROKER_RECONNECT_TIMEOUT);

        return new DefaultKafkaProducerFactory<>(kafkaConfig);
    }
}
