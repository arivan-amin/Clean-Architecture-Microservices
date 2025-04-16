package io.github.arivanamin.cam.backend.audit.core.query;

import io.github.arivanamin.cam.backend.audit.core.exception.AuditEventNotFoundException;
import io.github.arivanamin.cam.backend.audit.core.persistence.AuditEventStorage;
import io.github.arivanamin.cam.backend.base.domain.audit.AuditEvent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReadAuditEventByIdQuery {

    private final AuditEventStorage storage;

    public AuditEvent execute (String id) {
        return storage.findById(id)
            .orElseThrow(AuditEventNotFoundException::new);
    }
}
