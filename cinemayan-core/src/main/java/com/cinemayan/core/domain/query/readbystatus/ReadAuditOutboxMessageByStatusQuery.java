package com.cinemayan.core.domain.query.readbystatus;

import com.cinemayan.core.domain.audit.AuditEvent;
import com.cinemayan.core.domain.outbox.AuditOutboxMessage;
import com.cinemayan.core.domain.outbox.AuditOutboxMessageStorage;
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
