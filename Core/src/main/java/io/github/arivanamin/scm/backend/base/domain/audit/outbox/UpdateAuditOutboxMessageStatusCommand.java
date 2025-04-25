package io.github.arivanamin.scm.backend.base.domain.audit.outbox;

import io.github.arivanamin.scm.backend.base.domain.outbox.OutboxMessageStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
public class UpdateAuditOutboxMessageStatusCommand {

    private final AuditOutboxMessageStorage storage;

    public void execute (UUID messageId, OutboxMessageStatus status) {
        storage.updateMessageStatus(messageId, status);
    }
}
