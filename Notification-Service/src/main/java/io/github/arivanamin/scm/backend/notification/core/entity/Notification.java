package io.github.arivanamin.scm.backend.notification.core.entity;

import io.github.arivanamin.scm.backend.base.domain.notification.NotificationChannel;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults (level = AccessLevel.PRIVATE)
public class Notification {

    private String id;
    private NotificationChannel channel;
    private String recipient;
    private String content;
    private String referenceId;
    private long timestamp;
}
