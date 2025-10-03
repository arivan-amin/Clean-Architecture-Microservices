package io.github.arivanamin.scm.backend.outbox.application.config;

import io.github.arivanamin.scm.backend.base.core.outbox.AuditOutboxMessageStorage;
import io.github.arivanamin.scm.backend.outbox.storage.audit.AuditOutboxMessageRepository;
import io.github.arivanamin.scm.backend.outbox.storage.audit.JpaAuditOutboxMessageStorage;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
class OutboxStorageBeansConfig {

    @Bean
    @Primary
    public AuditOutboxMessageStorage auditOutboxMessageStorage (
        AuditOutboxMessageRepository repository) {
        return new JpaAuditOutboxMessageStorage(repository);
    }
}
