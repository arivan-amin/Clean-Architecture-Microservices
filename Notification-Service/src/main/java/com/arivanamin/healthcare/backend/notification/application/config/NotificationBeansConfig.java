package com.arivanamin.healthcare.backend.notification.application.config;

import com.arivanamin.healthcare.backend.notification.core.command.CreateNotificationCommand;
import com.arivanamin.healthcare.backend.notification.core.persistence.NotificationStorage;
import com.arivanamin.healthcare.backend.notification.storage.JpaNotificationStorage;
import com.arivanamin.healthcare.backend.notification.storage.NotificationRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
class NotificationBeansConfig {

    @Bean
    public NotificationStorage storage (NotificationRepository repository) {
        return new JpaNotificationStorage(repository);
    }

    @Bean
    public CreateNotificationCommand createNotificationCommand (NotificationStorage storage) {
        return new CreateNotificationCommand(storage);
    }
}
