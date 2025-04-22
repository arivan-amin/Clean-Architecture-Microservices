package io.github.arivanamin.scm.backend.notification.application.consumer;

import io.github.arivanamin.scm.backend.base.domain.aspects.LogExecutionTime;
import io.github.arivanamin.scm.backend.base.domain.notification.NotificationRequest;
import io.github.arivanamin.scm.backend.notification.core.command.CreateNotificationCommand;
import io.github.arivanamin.scm.backend.notification.core.entity.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static io.github.arivanamin.scm.backend.base.domain.topics.NotificationTopics.NOTIFICATION_TOPIC;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final CreateNotificationCommand command;

    @KafkaListener (topics = NOTIFICATION_TOPIC)
    @LogExecutionTime
    public void consumeNotification (NotificationRequest request) {
        saveToStorage(request);
    }

    private void saveToStorage (NotificationRequest request) {
        Notification notification = new ModelMapper().map(request, Notification.class);
        log.info("Consumed notification = {}", notification);
    }
}
