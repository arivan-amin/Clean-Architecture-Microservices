package com.arivanamin.healthcare.backend.notification.core.entity;

import com.arivanamin.healthcare.backend.base.domain.notification.NotificationChannel;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    private String id;
    private NotificationChannel channel;
    private String recipient;
    private String content;
    private String referenceId;
    private long timestamp;
}
