package io.github.arivanamin.scm.backend.notification.core.persistence;

import io.github.arivanamin.scm.backend.base.domain.pagination.PaginatedResponse;
import io.github.arivanamin.scm.backend.base.domain.pagination.PaginationCriteria;
import io.github.arivanamin.scm.backend.notification.core.entity.Notification;

import java.time.LocalDateTime;
import java.util.Optional;

public interface NotificationStorage {

    PaginatedResponse<Notification> findAll (LocalDateTime start, LocalDateTime end,
                                             PaginationCriteria criteria);

    PaginatedResponse<Notification> findAllByCriteria (Notification searchCriteria,
                                                       PaginationCriteria paginationCriteria);

    Optional<Notification> findById (String id);

    String create (Notification notification);
}
