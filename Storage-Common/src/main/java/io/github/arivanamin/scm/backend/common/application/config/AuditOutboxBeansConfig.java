package io.github.arivanamin.scm.backend.common.application.config;

import io.github.arivanamin.scm.backend.base.domain.audit.outbox.*;
import io.github.arivanamin.scm.backend.common.storage.audit.AuditOutboxMessageRepository;
import io.github.arivanamin.scm.backend.common.storage.audit.JpaAuditOutboxMessageStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
class AuditOutboxBeansConfig {

    @Bean
    public AuditOutboxMessageStorage auditOutboxMessageStorage (
        AuditOutboxMessageRepository repository) {
        return new JpaAuditOutboxMessageStorage(repository);
    }

    @Bean
    public ReadAuditOutboxMessageByStatusQuery readAuditOutboxMessageByStatusQuery (
        AuditOutboxMessageStorage storage) {
        return new ReadAuditOutboxMessageByStatusQuery(storage);
    }

    @Bean
    public ReadAuditOutboxMessageByIdQuery readAuditOutboxMessageByIdQuery (
        AuditOutboxMessageStorage storage) {
        return new ReadAuditOutboxMessageByIdQuery(storage);
    }

    @Bean
    public CreateAuditOutboxMessageCommand createAuditEventOutboxMessageCommand (
        AuditOutboxMessageStorage storage) {
        return new CreateAuditOutboxMessageCommand(storage);
    }

    @Bean
    public UpdateAuditOutboxMessageStatusCommand updateAuditOutboxMessageStatusCommand (
        AuditOutboxMessageStorage storage) {
        return new UpdateAuditOutboxMessageStatusCommand(storage);
    }

    @Bean
    public DeleteCompletedAuditOutboxMessagesCommand deleteCompletedAuditOutboxMessagesCommand (
        AuditOutboxMessageStorage storage) {
        return new DeleteCompletedAuditOutboxMessagesCommand(storage);
    }
}
