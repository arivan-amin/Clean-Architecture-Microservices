package io.github.arivanamin.scm.backend.audit.core.query;

import io.github.arivanamin.scm.backend.audit.core.exception.AuditEventNotFoundException;
import io.github.arivanamin.scm.backend.audit.core.persistence.AuditEventStorage;
import io.github.arivanamin.scm.backend.base.domain.audit.AuditEvent;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class ReadAuditEventByIdQuery {

    private final AuditEventStorage storage;

    public AuditEvent execute (UUID id) {
        return storage.findById(id)
            .orElseThrow(AuditEventNotFoundException::new);
    }
}
