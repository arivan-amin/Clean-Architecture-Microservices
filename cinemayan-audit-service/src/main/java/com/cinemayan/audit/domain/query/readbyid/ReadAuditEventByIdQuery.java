package com.cinemayan.audit.domain.query.readbyid;

import com.cinemayan.audit.domain.exception.AuditEventNotFoundException;
import com.cinemayan.audit.domain.persistence.AuditEventStorage;
import io.github.arivanamin.cinemayan.core.domain.audit.AuditEvent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReadAuditEventByIdQuery {

    private final AuditEventStorage storage;

    public ReadAuditEventByIdOutput execute (ReadAuditEventByIdInput input) {
        AuditEvent event = storage.findById(input.getId())
            .orElseThrow(AuditEventNotFoundException::new);
        return new ReadAuditEventByIdOutput(event);
    }
}
