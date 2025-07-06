package io.github.arivanamin.scm.backend.base.domain.query;

import io.github.arivanamin.scm.backend.base.domain.audit.AuditEvent;
import io.github.arivanamin.scm.backend.base.domain.outbox.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class ReadAuditOutboxMessageByStatusQuery {

    private final AuditOutboxMessageStorage storage;

    public List<AuditEvent> execute (OutboxMessageStatus status) {
        return storage.findAllByStatus(status)
            .stream()
            .map(AuditOutboxMessage::toDomain)
            .toList();
    }
}
