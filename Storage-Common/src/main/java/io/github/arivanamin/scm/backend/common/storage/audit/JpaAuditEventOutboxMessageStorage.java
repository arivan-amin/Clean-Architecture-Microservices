package io.github.arivanamin.scm.backend.common.storage.audit;

import io.github.arivanamin.scm.backend.base.domain.audit.AuditEvent;
import io.github.arivanamin.scm.backend.base.domain.audit.AuditEventOutboxMessageStorage;
import io.github.arivanamin.scm.backend.base.domain.outbox.OutboxMessageStatus;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
public class JpaAuditEventOutboxMessageStorage implements AuditEventOutboxMessageStorage {

    private final AuditEventOutboxMessageRepository repository;

    @Override
    public List<AuditEvent> findAllByStatus (OutboxMessageStatus status) {
        return List.of();
    }

    @Override
    public Optional<AuditEvent> findById (UUID id) {
        return Optional.empty();
    }

    @Override
    public UUID create (AuditEvent event) {
        return null;
    }

    @Override
    public void updateMessageStatus (UUID messageId, OutboxMessageStatus status) {

    }

    @Override
    public void deleteAllCompleted () {

    }
}
