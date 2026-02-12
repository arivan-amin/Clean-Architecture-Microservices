package io.github.arivanamin.lms.backend.outbox.application.config;

import io.github.arivanamin.lms.backend.core.domain.outbox.AuditOutboxMessageStorage;
import io.github.arivanamin.lms.backend.outbox.storage.audit.AuditOutboxMessageRepository;
import io.github.arivanamin.lms.backend.outbox.storage.audit.JpaAuditOutboxMessageStorage;
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
