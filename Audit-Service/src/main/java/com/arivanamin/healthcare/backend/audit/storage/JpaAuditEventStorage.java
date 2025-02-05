package com.arivanamin.healthcare.backend.audit.storage;

import com.arivanamin.healthcare.backend.audit.core.persistence.AuditEventStorage;
import com.arivanamin.healthcare.backend.base.domain.audit.AuditEvent;
import com.arivanamin.healthcare.backend.base.domain.pagination.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.arivanamin.healthcare.backend.audit.storage.JpaAuditEvent.fromDomain;
import static org.springframework.data.domain.ExampleMatcher.StringMatcher.CONTAINING;
import static org.springframework.data.domain.PageRequest.of;

@RequiredArgsConstructor
@Slf4j
public class JpaAuditEventStorage implements AuditEventStorage {

    private final AuditEventRepository repository;

    @Override
    public PaginatedResponse<AuditEvent> findAll (LocalDateTime start, LocalDateTime end,
                                                  PaginationCriteria criteria) {
        Page<JpaAuditEvent> page = fetchPaginatedEvents(criteria);
        List<AuditEvent> events = mapToDomainEntities(page.getContent());
        return buildPaginatedResponse(page, events);
    }

    private Page<JpaAuditEvent> fetchPaginatedEvents (PaginationCriteria criteria) {
        return repository.findAll(of(criteria.getPage(), criteria.getSize()));
    }

    private static List<AuditEvent> mapToDomainEntities (List<JpaAuditEvent> page) {
        return page.stream()
            .map(JpaAuditEvent::toDomain)
            .toList();
    }

    private PaginatedResponse<AuditEvent> buildPaginatedResponse (Page<JpaAuditEvent> page,
                                                                  List<AuditEvent> elements) {
        return PaginatedResponse.of(extractPageData(page), elements);
    }

    public PageData extractPageData (Page<JpaAuditEvent> page) {
        return PageData.of(page.getNumber(), page.getTotalPages(), page.getSize(),
            page.getTotalElements());
    }

    @Override
    public PaginatedResponse<AuditEvent> findAllByCriteria (AuditEvent searchCriteria,
                                                            PaginationCriteria paginationCriteria) {
        Page<JpaAuditEvent> page =
            fetchPaginatedEntitiesByCriteria(searchCriteria, paginationCriteria);
        List<AuditEvent> events = mapToDomainEntities(page.getContent());
        return buildPaginatedResponse(page, events);
    }

    private Page<JpaAuditEvent> fetchPaginatedEntitiesByCriteria (AuditEvent searchCriteria,
                                                                  PaginationCriteria paginationCriteria) {
        PageRequest pageable = of(paginationCriteria.getPage(), paginationCriteria.getSize());
        return repository.findAll(createExampleFromEvent(searchCriteria), pageable);
    }

    private static Example<JpaAuditEvent> createExampleFromEvent (AuditEvent event) {
        return Example.of(fromDomain(event), getExampleMatcher());
    }

    private static ExampleMatcher getExampleMatcher () {
        return ExampleMatcher.matching()
            .withIgnorePaths("id", "recordedAt")
            .withIgnoreNullValues()
            .withIgnoreCase()
            .withStringMatcher(CONTAINING);
    }

    @Override
    public Optional<AuditEvent> findById (String id) {
        return repository.findById(id)
            .map(JpaAuditEvent::toDomain);
    }

    @Transactional
    @Override
    public String create (AuditEvent event) {
        return repository.save(fromDomain(event))
            .getId();
    }
}
