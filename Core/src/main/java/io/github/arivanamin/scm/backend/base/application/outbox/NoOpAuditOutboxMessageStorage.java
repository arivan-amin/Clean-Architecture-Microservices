package io.github.arivanamin.scm.backend.base.application.outbox;

import io.github.arivanamin.scm.backend.base.domain.audit.AuditEvent;
import io.github.arivanamin.scm.backend.base.domain.dates.DateTimeRange;
import io.github.arivanamin.scm.backend.base.domain.outbox.*;
import io.github.arivanamin.scm.backend.base.domain.pagination.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

import java.util.*;

@ConditionalOnMissingBean (AuditOutboxMessageStorage.class)
@Slf4j
public class NoOpAuditOutboxMessageStorage implements AuditOutboxMessageStorage {

    PageData pageData = PageData.of(0, 1, 1, 1);

    @Override
    public List<AuditOutboxMessage> findAllByStatus (OutboxMessageStatus status) {
        return Collections.emptyList();
    }

    @Override
    public Optional<AuditOutboxMessage> findById (UUID id) {
        return Optional.empty();
    }

    @Override
    public UUID create (AuditOutboxMessage event) {
        log.info("NoOpAuditEventStorage used – skipping audit event: {}", event);
        return UUID.randomUUID();
    }

    @Override
    public void updateMessageStatus (UUID messageId, OutboxMessageStatus status) {

    }

    @Override
    public void deleteAllCompleted () {

    }

    public PaginatedResponse<AuditEvent> findAll (DateTimeRange range,
                                                  PaginationCriteria criteria) {
        return PaginatedResponse.of(pageData, Collections.emptyList());
    }
}
