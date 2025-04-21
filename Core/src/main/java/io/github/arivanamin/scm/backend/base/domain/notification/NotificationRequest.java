package io.github.arivanamin.scm.backend.base.domain.notification;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class NotificationRequest {

    private NotificationChannel channel;
    private String recipient;
    private String content;
    private String referenceId;
    private long timestamp;
}
