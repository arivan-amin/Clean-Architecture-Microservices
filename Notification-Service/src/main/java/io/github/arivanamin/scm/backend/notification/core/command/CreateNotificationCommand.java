package io.github.arivanamin.scm.backend.notification.core.command;

import io.github.arivanamin.scm.backend.notification.core.entity.Notification;
import io.github.arivanamin.scm.backend.notification.core.persistence.NotificationStorage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateNotificationCommand {

    private final NotificationStorage storage;

    public String execute (Notification notification) {
        return storage.create(notification);
    }
}
