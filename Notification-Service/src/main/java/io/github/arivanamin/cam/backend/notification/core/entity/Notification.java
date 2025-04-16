package io.github.arivanamin.cam.backend.notification.core.entity;

import io.github.arivanamin.cam.backend.base.domain.notification.NotificationChannel;
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
