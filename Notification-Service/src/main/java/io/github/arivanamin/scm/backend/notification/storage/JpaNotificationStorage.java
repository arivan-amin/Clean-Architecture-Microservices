package io.github.arivanamin.scm.backend.notification.storage;

import io.github.arivanamin.scm.backend.base.domain.pagination.PaginatedResponse;
import io.github.arivanamin.scm.backend.base.domain.pagination.PaginationCriteria;
import io.github.arivanamin.scm.backend.notification.core.entity.Notification;
import io.github.arivanamin.scm.backend.notification.core.persistence.NotificationStorage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class JpaNotificationStorage implements NotificationStorage {

    private final NotificationRepository repository;

    @Override
    public PaginatedResponse<Notification> findAll (LocalDateTime start, LocalDateTime end,
                                                    PaginationCriteria criteria) {
        return PaginatedResponse.of(null, Collections.emptyList());
    }

    @Override
    public PaginatedResponse<Notification> findAllByCriteria (Notification searchCriteria,
                                                              PaginationCriteria paginationCriteria) {
        return PaginatedResponse.of(null, Collections.emptyList());
    }

    @Override
    public Optional<Notification> findById (String id) {
        return Optional.empty();
    }

    @Transactional
    @Override
    public String create (Notification notification) {
        return "";
    }
}
