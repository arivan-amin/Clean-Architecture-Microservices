package io.github.arivanamin.lms.backend.base.core.query;

import io.github.arivanamin.lms.backend.base.core.audit.AuditEvent;
import io.github.arivanamin.lms.backend.base.core.outbox.*;
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
