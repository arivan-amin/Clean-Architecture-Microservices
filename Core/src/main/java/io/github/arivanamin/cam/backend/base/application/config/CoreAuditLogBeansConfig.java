package io.github.arivanamin.cam.backend.base.application.config;

import io.github.arivanamin.cam.backend.base.application.audit.AuditDataExtractor;
import io.github.arivanamin.cam.backend.base.application.audit.KafkaAuditEventPublisher;
import io.github.arivanamin.cam.backend.base.domain.audit.AuditEvent;
import io.github.arivanamin.cam.backend.base.domain.audit.AuditEventPublisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
class CoreAuditLogBeansConfig {

    @Bean
    public AuditDataExtractor auditDataExtractor (
        @Value ("${spring.application.name}") String serviceName) {
        return new AuditDataExtractor(serviceName);
    }

    @Bean
    public AuditEventPublisher auditEventPublisher (
        KafkaTemplate<String, AuditEvent> kafkaTemplate) {
        return new KafkaAuditEventPublisher(kafkaTemplate);
    }
}
