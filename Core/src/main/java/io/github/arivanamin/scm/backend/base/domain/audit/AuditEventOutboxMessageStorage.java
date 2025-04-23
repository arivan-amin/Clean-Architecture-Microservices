package io.github.arivanamin.scm.backend.base.domain.audit;

import io.github.arivanamin.scm.backend.base.domain.outbox.OutboxMessageStatus;

import java.util.*;

public interface AuditEventOutboxMessageStorage {

    List<AuditEvent> findAllByStatus (OutboxMessageStatus status);

    Optional<AuditEvent> findById (UUID id);

    UUID create (AuditEvent event);

    void updateMessageStatus (UUID messageId, OutboxMessageStatus status);

    void deleteAllCompleted ();
}
