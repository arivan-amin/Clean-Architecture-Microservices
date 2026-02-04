package io.github.arivanamin.lms.backend.core.domain.command;

import io.github.arivanamin.lms.backend.core.domain.outbox.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class CreateAuditOutboxMessageCommand {

    private final AuditOutboxMessageStorage storage;

    public CreateAuditOutboxMessageCommandOutput execute (
        CreateAuditOutboxMessageCommandInput input) {

        AuditOutboxMessage message = AuditOutboxMessage.fromDomain(input.getAuditEvent());
        message.setStatus(OutboxMessageStatus.PENDING);

        return new CreateAuditOutboxMessageCommandOutput(storage.create(message));
    }
}
