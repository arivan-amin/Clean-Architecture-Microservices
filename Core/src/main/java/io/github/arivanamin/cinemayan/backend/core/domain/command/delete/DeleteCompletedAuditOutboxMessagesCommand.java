package io.github.arivanamin.cinemayan.backend.core.domain.command.delete;

import io.github.arivanamin.cinemayan.backend.core.domain.outbox.AuditOutboxMessageStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class DeleteCompletedAuditOutboxMessagesCommand {

    private final AuditOutboxMessageStorage storage;

    public void execute () {
        storage.deleteAllCompleted();
    }
}
