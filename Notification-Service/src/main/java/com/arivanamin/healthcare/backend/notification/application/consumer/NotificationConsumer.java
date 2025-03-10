package com.arivanamin.healthcare.backend.notification.application.consumer;

import com.arivanamin.healthcare.backend.base.domain.aspects.LogExecutionTime;
import com.arivanamin.healthcare.backend.base.domain.notification.NotificationRequest;
import com.arivanamin.healthcare.backend.notification.core.command.CreateNotificationCommand;
import com.arivanamin.healthcare.backend.notification.core.entity.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.arivanamin.healthcare.backend.base.domain.topics.NotificationTopics.NOTIFICATION_TOPIC;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final CreateNotificationCommand command;

    @Value ("${spring.kafka.consumer.group-id}")
    String notificationGroupId;

    @KafkaListener (topics = NOTIFICATION_TOPIC, groupId = "notificationGroupId")
    @LogExecutionTime
    public void consumeNotification (NotificationRequest request) {
        saveToStorage(request);
    }

    private void saveToStorage (NotificationRequest request) {
        Notification notification = new ModelMapper().map(request, Notification.class);
        log.info("Consumed notification = {}", notification);
        String savedNotificationId = command.execute(notification);
        log.info("savedNotificationId = {}", savedNotificationId);
    }
}
