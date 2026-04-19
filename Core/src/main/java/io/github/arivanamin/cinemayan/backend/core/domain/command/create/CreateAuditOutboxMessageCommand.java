package io.github.arivanamin.cinemayan.backend.core.domain.command.create;

import io.github.arivanamin.cinemayan.backend.core.domain.outbox.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class CreateAuditOutboxMessageCommand {

    private final AuditOutboxMessageStorage storage;

    public CreateAuditOutboxMessageOutput execute (CreateAuditOutboxMessageInput input) {

        AuditOutboxMessage message = AuditOutboxMessage.fromDomain(input.getAuditEvent());
        message.setStatus(OutboxMessageStatus.PENDING);

        return new CreateAuditOutboxMessageOutput(storage.create(message));
    }
}
