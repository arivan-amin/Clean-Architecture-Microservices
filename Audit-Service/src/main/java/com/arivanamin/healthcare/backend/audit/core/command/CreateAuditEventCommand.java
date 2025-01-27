package com.arivanamin.healthcare.backend.audit.core.command;

import com.arivanamin.healthcare.backend.audit.core.persistence.AuditEventStorage;
import com.arivanamin.healthcare.backend.base.domain.audit.AuditEvent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateAuditEventCommand {

    private final AuditEventStorage storage;

    public String execute (AuditEvent patient) {
        return storage.create(patient);
    }
}
