package io.github.arivanamin.lms.backend.core.domain.query.readbyid;

import io.github.arivanamin.lms.backend.core.domain.outbox.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class ReadAuditOutboxMessageByIdQuery {

    private final AuditOutboxMessageStorage storage;

    public ReadAuditOutboxMessageByIdOutput execute (ReadAuditOutboxMessageByIdInput input) {

        AuditOutboxMessage event = storage.findById(input.getId())
            .orElseThrow(AuditOutboxMessageNotFound::new);

        return new ReadAuditOutboxMessageByIdOutput(event.toDomain());
    }
}
