package io.github.arivanamin.lms.backend.core.domain.command;

import io.github.arivanamin.lms.backend.core.domain.outbox.AuditOutboxMessageStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class UpdateAuditOutboxMessageStatusCommand {

    private final AuditOutboxMessageStorage storage;

    public void execute (UpdateAuditOutboxMessageStatusCommandInput input) {
        storage.updateMessageStatus(input.getMessageId(), input.getStatus());
    }
}
