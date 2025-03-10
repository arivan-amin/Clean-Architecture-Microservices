package com.arivanamin.healthcare.backend.notification.core.persistence;

import com.arivanamin.healthcare.backend.base.domain.pagination.PaginatedResponse;
import com.arivanamin.healthcare.backend.base.domain.pagination.PaginationCriteria;
import com.arivanamin.healthcare.backend.notification.core.entity.Notification;

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
