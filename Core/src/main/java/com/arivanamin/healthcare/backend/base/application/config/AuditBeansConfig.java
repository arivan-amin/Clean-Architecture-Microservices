package com.arivanamin.healthcare.backend.base.application.config;

import com.arivanamin.healthcare.backend.base.application.audit.AuditDataExtractor;
import com.arivanamin.healthcare.backend.base.application.audit.KafkaAuditEventPublisher;
import com.arivanamin.healthcare.backend.base.domain.audit.AuditEvent;
import com.arivanamin.healthcare.backend.base.domain.audit.AuditEventPublisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
class AuditBeansConfig {

    @Bean
    public AuditDataExtractor auditDataExtractor (
        @Value ("${spring.application.name}") String appName) {
        return new AuditDataExtractor(appName);
    }

    @Bean
    public AuditEventPublisher publisher (KafkaTemplate<String, AuditEvent> kafkaTemplate) {
        return new KafkaAuditEventPublisher(kafkaTemplate);
    }
}
