package com.cinemayan.core.domain.command.delete;

import com.cinemayan.core.domain.outbox.AuditOutboxMessageStorage;
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
