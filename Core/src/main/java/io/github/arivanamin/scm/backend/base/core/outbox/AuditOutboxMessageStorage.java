package io.github.arivanamin.scm.backend.base.core.outbox;

import java.util.*;

public interface AuditOutboxMessageStorage {

    List<AuditOutboxMessage> findAllByStatus (OutboxMessageStatus status);

    Optional<AuditOutboxMessage> findById (UUID id);

    UUID create (AuditOutboxMessage event);

    void updateMessageStatus (UUID messageId, OutboxMessageStatus status);

    void deleteAllCompleted ();
}
