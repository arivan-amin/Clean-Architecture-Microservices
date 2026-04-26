package com.cinemayan.outbox.application.config;

import com.cinemayan.core.domain.outbox.AuditOutboxMessageStorage;
import com.cinemayan.outbox.infrastructure.audit.AuditOutboxMessageRepository;
import com.cinemayan.outbox.infrastructure.audit.JpaAuditOutboxMessageStorage;
import org.springframework.context.annotation.*;

@Configuration
class OutboxStorageBeansConfig {

    @Bean
    @Primary
    public AuditOutboxMessageStorage auditOutboxMessageStorage (
        AuditOutboxMessageRepository repository) {
        return new JpaAuditOutboxMessageStorage(repository);
    }
}
