package io.github.arivanamin.cam.backend.audit.core.command;

import io.github.arivanamin.cam.backend.audit.core.persistence.AuditEventStorage;
import io.github.arivanamin.cam.backend.base.domain.audit.AuditEvent;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class CreateAuditEventCommand {

    private final AuditEventStorage storage;

    public UUID execute (AuditEvent patient) {
        return storage.create(patient);
    }
}
