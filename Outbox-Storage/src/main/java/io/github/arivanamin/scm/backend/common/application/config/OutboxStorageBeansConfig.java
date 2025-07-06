package io.github.arivanamin.scm.backend.common.application.config;

import io.github.arivanamin.scm.backend.base.domain.outbox.AuditOutboxMessageStorage;
import io.github.arivanamin.scm.backend.common.storage.audit.AuditOutboxMessageRepository;
import io.github.arivanamin.scm.backend.common.storage.audit.JpaAuditOutboxMessageStorage;
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
