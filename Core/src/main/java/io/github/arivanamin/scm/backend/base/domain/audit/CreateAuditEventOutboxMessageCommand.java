package io.github.arivanamin.scm.backend.base.domain.audit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
public class CreateAuditEventOutboxMessageCommand {

    private final AuditEventOutboxMessageStorage storage;

    public UUID execute (AuditEvent auditEvent) {
        log.info("command received auditEvent = {}", auditEvent);
        return storage.create(auditEvent);
    }
}
