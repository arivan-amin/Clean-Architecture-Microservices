package io.github.arivanamin.cinemayan.core.domain.query.readbystatus;

import io.github.arivanamin.cinemayan.core.domain.audit.AuditEvent;
import io.github.arivanamin.cinemayan.core.domain.outbox.AuditOutboxMessage;
import io.github.arivanamin.cinemayan.core.domain.outbox.AuditOutboxMessageStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class ReadAuditOutboxMessageByStatusQuery {

    private final AuditOutboxMessageStorage storage;

    public ReadAuditOutboxMessageByStatusOutput execute (
        ReadAuditOutboxMessageByStatusInput input) {

        List<AuditEvent> events = storage.findAllByStatus(input.getStatus())
            .stream()
            .map(AuditOutboxMessage::toDomain)
            .toList();

        return new ReadAuditOutboxMessageByStatusOutput(events);
    }
}
