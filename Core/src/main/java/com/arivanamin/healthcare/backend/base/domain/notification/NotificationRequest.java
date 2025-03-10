package com.arivanamin.healthcare.backend.base.domain.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Map;

@AllArgsConstructor
@Builder
public final class NotificationRequest {

    private final String templateName;
    private final Map<String, String> variables;
    private NotificationChannel channel;
    private String recipient;
    private String timestamp;
}
