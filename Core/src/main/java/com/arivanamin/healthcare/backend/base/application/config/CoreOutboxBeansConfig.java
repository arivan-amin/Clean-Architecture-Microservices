package com.arivanamin.healthcare.backend.base.application.config;

import com.arivanamin.healthcare.backend.base.application.outbox.KafkaOutboxPublisher;
import com.arivanamin.healthcare.backend.base.domain.outbox.OutboxEvent;
import com.arivanamin.healthcare.backend.base.domain.outbox.OutboxPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
class CoreOutboxBeansConfig {

    @Bean
    public OutboxPublisher outboxPublisher (KafkaTemplate<String, OutboxEvent> kafkaTemplate) {
        return new KafkaOutboxPublisher(kafkaTemplate);
    }
}
