package io.github.arivanamin.cam.backend.notification.application.beans;

import io.github.arivanamin.cam.backend.notification.core.command.CreateNotificationCommand;
import io.github.arivanamin.cam.backend.notification.core.persistence.NotificationStorage;
import io.github.arivanamin.cam.backend.notification.storage.JpaNotificationStorage;
import io.github.arivanamin.cam.backend.notification.storage.NotificationRepository;
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
