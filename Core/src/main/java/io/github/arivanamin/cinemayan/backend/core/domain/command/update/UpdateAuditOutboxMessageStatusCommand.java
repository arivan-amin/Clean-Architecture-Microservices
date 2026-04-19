package io.github.arivanamin.cinemayan.backend.core.domain.command.update;

import io.github.arivanamin.cinemayan.backend.core.domain.outbox.AuditOutboxMessageStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class UpdateAuditOutboxMessageStatusCommand {

    private final AuditOutboxMessageStorage storage;

    public void execute (UpdateAuditOutboxMessageStatusInput input) {
        storage.updateMessageStatus(input.getMessageId(), input.getStatus());
    }
}
