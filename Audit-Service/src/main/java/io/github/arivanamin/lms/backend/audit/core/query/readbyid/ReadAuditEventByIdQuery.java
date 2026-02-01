package io.github.arivanamin.lms.backend.audit.core.query.readbyid;

import io.github.arivanamin.lms.backend.audit.core.exception.AuditEventNotFoundException;
import io.github.arivanamin.lms.backend.audit.core.persistence.AuditEventStorage;
import io.github.arivanamin.lms.backend.base.core.audit.AuditEvent;
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
