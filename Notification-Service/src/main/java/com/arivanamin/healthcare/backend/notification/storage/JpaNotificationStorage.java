package com.arivanamin.healthcare.backend.notification.storage;

import com.arivanamin.healthcare.backend.base.domain.pagination.*;
import com.arivanamin.healthcare.backend.notification.core.entity.Notification;
import com.arivanamin.healthcare.backend.notification.core.persistence.NotificationStorage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.arivanamin.healthcare.backend.notification.storage.JpaNotification.fromDomain;
import static org.springframework.data.domain.ExampleMatcher.StringMatcher.CONTAINING;
import static org.springframework.data.domain.PageRequest.of;

@RequiredArgsConstructor
@Slf4j
public class JpaNotificationStorage implements NotificationStorage {

    private final NotificationRepository repository;

    @Override
    public PaginatedResponse<Notification> findAll (LocalDateTime start, LocalDateTime end,
                                                    PaginationCriteria criteria) {
        Page<JpaNotification> page = fetchPaginatedNotifications(criteria);
        List<Notification> notifications = mapToDomainEntities(page.getContent());
        return buildPaginatedResponse(page, notifications);
    }

    private Page<JpaNotification> fetchPaginatedNotifications (PaginationCriteria criteria) {
        return repository.findAll(of(criteria.getPage(), criteria.getSize()));
    }

    private static List<Notification> mapToDomainEntities (List<JpaNotification> page) {
        return page.stream()
            .map(JpaNotification::toDomain)
            .toList();
    }

    private PaginatedResponse<Notification> buildPaginatedResponse (Page<JpaNotification> page,
                                                                    List<Notification> elements) {
        return PaginatedResponse.of(extractPageData(page), elements);
    }

    public PageData extractPageData (Page<JpaNotification> page) {
        return PageData.of(page.getNumber(), page.getTotalPages(), page.getSize(),
            page.getTotalElements());
    }

    @Override
    public PaginatedResponse<Notification> findAllByCriteria (Notification searchCriteria,
                                                              PaginationCriteria paginationCriteria) {
        Page<JpaNotification> page =
            fetchPaginatedEntitiesByCriteria(searchCriteria, paginationCriteria);
        List<Notification> notifications = mapToDomainEntities(page.getContent());
        return buildPaginatedResponse(page, notifications);
    }

    private Page<JpaNotification> fetchPaginatedEntitiesByCriteria (Notification searchCriteria,
                                                                    PaginationCriteria paginationCriteria) {
        PageRequest pageable = of(paginationCriteria.getPage(), paginationCriteria.getSize());
        return repository.findAll(createExampleFromNotification(searchCriteria), pageable);
    }

    private static Example<JpaNotification> createExampleFromNotification (
        Notification notification) {
        return Example.of(fromDomain(notification), getExampleMatcher());
    }

    private static ExampleMatcher getExampleMatcher () {
        return ExampleMatcher.matching()
            .withIgnorePaths("id", "recordedAt")
            .withIgnoreNullValues()
            .withIgnoreCase()
            .withStringMatcher(CONTAINING);
    }

    @Override
    public Optional<Notification> findById (String id) {
        return repository.findById(id)
            .map(JpaNotification::toDomain);
    }

    @Transactional
    @Override
    public String create (Notification notification) {
        return repository.save(fromDomain(notification))
            .getId();
    }
}
