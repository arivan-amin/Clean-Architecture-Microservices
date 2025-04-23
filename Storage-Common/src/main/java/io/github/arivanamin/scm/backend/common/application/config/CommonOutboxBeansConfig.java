package io.github.arivanamin.scm.backend.common.application.config;

import io.github.arivanamin.scm.backend.base.domain.audit.AuditEventOutboxMessageStorage;
import io.github.arivanamin.scm.backend.base.domain.audit.CreateAuditEventOutboxMessageCommand;
import io.github.arivanamin.scm.backend.common.storage.audit.AuditEventOutboxMessageRepository;
import io.github.arivanamin.scm.backend.common.storage.audit.JpaAuditEventOutboxMessageStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
class CommonOutboxBeansConfig {

    @Bean
    public AuditEventOutboxMessageStorage auditOutboxMessageStorage (
        AuditEventOutboxMessageRepository repository) {
        return new JpaAuditEventOutboxMessageStorage(repository);
    }

    @Bean
    public CreateAuditEventOutboxMessageCommand createAuditEventOutboxMessageCommand (
        AuditEventOutboxMessageStorage storage) {
        return new CreateAuditEventOutboxMessageCommand(storage);
    }
}
