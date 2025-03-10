package com.arivanamin.healthcare.backend.base.domain.notification;

@FunctionalInterface
public interface NotificationPublisher {

    void sendNotification (String topic, NotificationRequest event);
}
