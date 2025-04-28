package io.github.arivanamin.scm.backend.audit.core.query;

import io.github.arivanamin.scm.backend.base.domain.audit.AuditEvent;
import io.github.arivanamin.scm.backend.base.domain.audit.AuditEventStorage;
import io.github.arivanamin.scm.backend.common.domain.exception.AuditEventNotFoundException;
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
