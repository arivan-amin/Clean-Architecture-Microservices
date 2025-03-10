package com.arivanamin.healthcare.backend.notification.core.command;

import com.arivanamin.healthcare.backend.notification.core.entity.Notification;
import com.arivanamin.healthcare.backend.notification.core.persistence.NotificationStorage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateNotificationCommand {

    private final NotificationStorage storage;

    public String execute (Notification notification) {
        return storage.create(notification);
    }
}
