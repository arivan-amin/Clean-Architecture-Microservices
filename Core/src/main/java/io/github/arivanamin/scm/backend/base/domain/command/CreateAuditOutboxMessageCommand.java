package io.github.arivanamin.scm.backend.base.domain.command;

import io.github.arivanamin.scm.backend.base.domain.audit.AuditEvent;
import io.github.arivanamin.scm.backend.base.domain.outbox.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
public class CreateAuditOutboxMessageCommand {

    private final AuditOutboxMessageStorage storage;

    public UUID execute (AuditEvent auditEvent) {
        AuditOutboxMessage message = AuditOutboxMessage.fromDomain(auditEvent);
        message.setStatus(OutboxMessageStatus.PENDING);
        return storage.create(message);
    }
}
