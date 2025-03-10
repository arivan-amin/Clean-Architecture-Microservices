package com.arivanamin.healthcare.backend.notification.storage;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;

public interface NotificationRepository extends MongoRepository<JpaNotification, String> {

    Page<JpaNotification> findAllByRecordedAtBetween (LocalDateTime start, LocalDateTime end,
                                                      Pageable pageable);
}
