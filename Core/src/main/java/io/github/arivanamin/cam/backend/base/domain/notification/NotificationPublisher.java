package io.github.arivanamin.cam.backend.base.domain.notification;

@FunctionalInterface
public interface NotificationPublisher {

    void sendNotification (String topic, NotificationRequest event);
}
