package io.github.arivanamin.scm.backend.base.application.audit;

import io.github.arivanamin.scm.backend.base.domain.audit.AuditEvent;
import io.github.arivanamin.scm.backend.base.domain.audit.AuditEventStorage;
import io.github.arivanamin.scm.backend.base.domain.dates.DateTimeRange;
import io.github.arivanamin.scm.backend.base.domain.pagination.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

import java.util.*;

@ConditionalOnMissingBean (AuditEventStorage.class)
@Slf4j
public class NoOpAuditEventStorage implements AuditEventStorage {

    PageData pageData = PageData.of(0, 1, 1, 1);

    @Override
    public PaginatedResponse<AuditEvent> findAll (DateTimeRange range,
                                                  PaginationCriteria criteria) {
        return PaginatedResponse.of(pageData, Collections.emptyList());
    }

    @Override
    public Optional<AuditEvent> findById (UUID id) {
        return Optional.empty();
    }

    @Override
    public UUID create (AuditEvent event) {
        log.info("NoOpAuditEventStorage used – skipping audit event: {}", event);
        return UUID.randomUUID();
    }
}
