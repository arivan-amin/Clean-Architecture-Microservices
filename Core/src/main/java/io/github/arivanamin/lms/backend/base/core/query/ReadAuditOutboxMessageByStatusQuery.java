package io.github.arivanamin.lms.backend.base.core.query;

import io.github.arivanamin.lms.backend.base.core.audit.AuditEvent;
import io.github.arivanamin.lms.backend.base.core.outbox.AuditOutboxMessage;
import io.github.arivanamin.lms.backend.base.core.outbox.AuditOutboxMessageStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class ReadAuditOutboxMessageByStatusQuery {

    private final AuditOutboxMessageStorage storage;

    public ReadAuditOutboxMessageByStatusQueryOutput execute (
        ReadAuditOutboxMessageByStatusQueryInput input) {

        List<AuditEvent> events = storage.findAllByStatus(input.getStatus())
            .stream()
            .map(AuditOutboxMessage::toDomain)
            .toList();

        return new ReadAuditOutboxMessageByStatusQueryOutput(events);
    }
}
