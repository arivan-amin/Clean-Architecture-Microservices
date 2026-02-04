package io.github.arivanamin.lms.backend.audit.domain.query.readbyid;

import io.github.arivanamin.lms.backend.audit.domain.exception.AuditEventNotFoundException;
import io.github.arivanamin.lms.backend.audit.domain.persistence.AuditEventStorage;
import io.github.arivanamin.lms.backend.core.domain.audit.AuditEvent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReadAuditEventByIdQuery {

    private final AuditEventStorage storage;

    public ReadAuditEventByIdQueryOutput execute (ReadAuditEventByIdQueryInput input) {
        AuditEvent event = storage.findById(input.getId())
            .orElseThrow(AuditEventNotFoundException::new);
        return new ReadAuditEventByIdQueryOutput(event);
    }
}
