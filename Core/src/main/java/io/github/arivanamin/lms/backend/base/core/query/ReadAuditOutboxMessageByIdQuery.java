package io.github.arivanamin.lms.backend.base.core.query;

import io.github.arivanamin.lms.backend.base.core.outbox.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class ReadAuditOutboxMessageByIdQuery {

    private final AuditOutboxMessageStorage storage;

    public ReadAuditOutboxMessageByIdQueryOutput execute (
        ReadAuditOutboxMessageByIdQueryInput input) {

        AuditOutboxMessage event = storage.findById(input.getId())
            .orElseThrow(AuditOutboxMessageNotFound::new);

        return new ReadAuditOutboxMessageByIdQueryOutput(event.toDomain());
    }
}
