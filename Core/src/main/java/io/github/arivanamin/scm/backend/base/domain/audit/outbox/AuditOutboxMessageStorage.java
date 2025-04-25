package io.github.arivanamin.scm.backend.base.domain.audit.outbox;

import io.github.arivanamin.scm.backend.base.domain.outbox.OutboxMessageStatus;

import java.util.*;

public interface AuditOutboxMessageStorage {

    List<AuditOutboxMessage> findAllByStatus (OutboxMessageStatus status);

    Optional<AuditOutboxMessage> findById (UUID id);

    UUID create (AuditOutboxMessage event);

    void updateMessageStatus (UUID messageId, OutboxMessageStatus status);

    void deleteAllCompleted ();
}
