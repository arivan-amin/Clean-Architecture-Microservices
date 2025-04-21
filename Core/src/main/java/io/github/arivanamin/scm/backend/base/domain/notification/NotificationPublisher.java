package io.github.arivanamin.scm.backend.base.domain.notification;

@FunctionalInterface
public interface NotificationPublisher {

    void sendNotification (String topic, NotificationRequest event);
}
