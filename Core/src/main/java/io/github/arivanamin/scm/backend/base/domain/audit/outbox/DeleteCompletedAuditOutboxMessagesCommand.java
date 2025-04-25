package io.github.arivanamin.scm.backend.base.domain.audit.outbox;

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
