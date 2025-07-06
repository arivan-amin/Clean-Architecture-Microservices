package io.github.arivanamin.scm.backend.base.domain.query;

import io.github.arivanamin.scm.backend.base.domain.audit.AuditEvent;
import io.github.arivanamin.scm.backend.base.domain.outbox.*;
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
