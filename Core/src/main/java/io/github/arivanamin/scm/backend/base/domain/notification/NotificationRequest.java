package io.github.arivanamin.scm.backend.base.domain.notification;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults (level = AccessLevel.PRIVATE)
public final class NotificationRequest {

    NotificationChannel channel;
    String recipient;
    String content;
    String referenceId;
    long timestamp;
}
