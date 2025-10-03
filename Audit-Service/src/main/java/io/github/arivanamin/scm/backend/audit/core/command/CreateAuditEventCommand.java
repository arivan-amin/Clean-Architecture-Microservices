package io.github.arivanamin.scm.backend.audit.core.command;

import io.github.arivanamin.scm.backend.audit.core.persistence.AuditEventStorage;
import io.github.arivanamin.scm.backend.base.core.audit.AuditEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
public class CreateAuditEventCommand {

    private final AuditEventStorage storage;

    public UUID execute (AuditEvent event) {
        return storage.create(event);
    }
}
