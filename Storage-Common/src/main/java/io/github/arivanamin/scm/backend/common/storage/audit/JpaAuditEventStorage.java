package io.github.arivanamin.scm.backend.common.storage.audit;

import io.github.arivanamin.scm.backend.base.domain.audit.AuditEvent;
import io.github.arivanamin.scm.backend.base.domain.audit.AuditEventStorage;
import io.github.arivanamin.scm.backend.base.domain.dates.DateTimeRange;
import io.github.arivanamin.scm.backend.base.domain.pagination.PaginatedResponse;
import io.github.arivanamin.scm.backend.base.domain.pagination.PaginationCriteria;
import io.github.arivanamin.scm.backend.common.domain.util.PaginationHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static io.github.arivanamin.scm.backend.common.storage.audit.JpaAuditEvent.fromDomain;
import static org.springframework.data.domain.PageRequest.of;

@RequiredArgsConstructor
@Slf4j
public class JpaAuditEventStorage implements AuditEventStorage {

    private final AuditEventRepository repository;

    @Override
    public PaginatedResponse<AuditEvent> findAll (DateTimeRange range,
                                                  PaginationCriteria criteria) {
        Page<JpaAuditEvent> page = fetchPaginatedEvents(range, criteria);
        List<AuditEvent> events = mapToDomainEntities(page.getContent());
        return PaginationHelper.buildPaginatedResponse(events, page);
    }

    private Page<JpaAuditEvent> fetchPaginatedEvents (DateTimeRange range,
                                                      PaginationCriteria criteria) {
        PageRequest pageRequest = createPageRequestAndSortByRecordedAt(criteria);
        return repository.findAllByRecordedAtBetween(range.getStart(), range.getEnd(), pageRequest);
    }

    private static List<AuditEvent> mapToDomainEntities (List<JpaAuditEvent> page) {
        return page.stream()
            .map(JpaAuditEvent::toDomain)
            .toList();
    }

    private PageRequest createPageRequestAndSortByRecordedAt (PaginationCriteria criteria) {
        return of(criteria.getPage(), criteria.getSize(), sortByRecordedAtDescending());
    }

    private Sort sortByRecordedAtDescending () {
        return Sort.by(Sort.Direction.DESC, "recordedAt");
    }

    @Override
    public Optional<AuditEvent> findById (UUID id) {
        return repository.findById(id)
            .map(JpaAuditEvent::toDomain);
    }

    @Transactional
    @Override
    public UUID create (AuditEvent event) {
        return repository.save(fromDomain(event))
            .getId();
    }
}
