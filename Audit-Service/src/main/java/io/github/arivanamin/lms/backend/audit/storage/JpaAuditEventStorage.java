package io.github.arivanamin.lms.backend.audit.storage;

import io.github.arivanamin.lms.backend.audit.domain.persistence.AuditEventStorage;
import io.github.arivanamin.lms.backend.audit.domain.persistence.ReadAuditEventsParams;
import io.github.arivanamin.lms.backend.core.domain.audit.AuditEvent;
import io.github.arivanamin.lms.backend.core.domain.pagination.PaginatedResponse;
import io.github.arivanamin.lms.backend.core.domain.pagination.PaginationCriteria;
import io.github.arivanamin.lms.backend.outbox.storage.util.PaginationHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static io.github.arivanamin.lms.backend.audit.storage.JpaAuditEvent.fromDomain;

@RequiredArgsConstructor
@Slf4j
public class JpaAuditEventStorage implements AuditEventStorage {

    private final AuditEventRepository repository;

    @Override
    public PaginatedResponse<AuditEvent> findAll (ReadAuditEventsParams params) {
        Page<JpaAuditEvent> page = fetchPaginatedEvents(params);
        List<AuditEvent> events = mapToDomainEntities(page.getContent());
        return PaginationHelper.buildPaginatedResponse(events, page);
    }

    private Page<JpaAuditEvent> fetchPaginatedEvents (ReadAuditEventsParams params) {
        PageRequest pageRequest = createPageRequestAndSortByRecordedAt(params.getCriteria());
        return repository.findAllByRecordedAtBetween(params.getStartDate(), params.getEndDate(),
            pageRequest);
    }

    private static List<AuditEvent> mapToDomainEntities (List<JpaAuditEvent> page) {
        return page.stream()
            .map(JpaAuditEvent::toDomain)
            .toList();
    }

    private PageRequest createPageRequestAndSortByRecordedAt (PaginationCriteria criteria) {
        return PageRequest.of(criteria.getPage(), criteria.getSize(), sortByRecordedAtDescending());
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
