package io.github.arivanamin.scm.backend.notification.storage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<JpaNotification, String> {

}
