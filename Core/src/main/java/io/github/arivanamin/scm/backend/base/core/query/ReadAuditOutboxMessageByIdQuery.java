package io.github.arivanamin.scm.backend.base.core.query;

import io.github.arivanamin.scm.backend.base.core.audit.AuditEvent;
import io.github.arivanamin.scm.backend.base.core.outbox.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
public class ReadAuditOutboxMessageByIdQuery {

    private final AuditOutboxMessageStorage storage;

    public AuditEvent execute (UUID id) {
        AuditOutboxMessage event = storage.findById(id)
            .orElseThrow(AuditOutboxMessageNotFound::new);
        return event.toDomain();
    }
}
