package io.github.arivanamin.lms.backend.audit.domain.query.readbyid;

import io.github.arivanamin.cinemayan.backend.core.domain.audit.AuditEvent;
import io.github.arivanamin.lms.backend.audit.domain.exception.AuditEventNotFoundException;
import io.github.arivanamin.lms.backend.audit.domain.persistence.AuditEventStorage;
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
