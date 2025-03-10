package com.arivanamin.healthcare.backend.notification.storage;

import com.arivanamin.healthcare.backend.base.domain.notification.NotificationChannel;
import com.arivanamin.healthcare.backend.notification.core.entity.Notification;
import jakarta.persistence.Id;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

import static com.arivanamin.healthcare.backend.base.domain.dates.TimestampHelper.toLocalDateTime;
import static com.arivanamin.healthcare.backend.base.domain.dates.TimestampHelper.toTimestampInMilliseconds;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class JpaNotification {

    @Id
    String id;
    private NotificationChannel channel;
    private String recipient;
    private LocalDateTime recordedAt;
    private String templateName;
    private Map<String, String> variables;

    public static JpaNotification fromDomain (Notification notification) {
        JpaNotification jpaNotification =
            new ModelMapper().map(notification, JpaNotification.class);
        jpaNotification.setRecordedAt(toLocalDateTime(notification.getTimestamp()));
        return jpaNotification;
    }

    public Notification toDomain () {
        Notification notification = new ModelMapper().map(this, Notification.class);
        notification.setTimestamp(toTimestampInMilliseconds(recordedAt));
        return notification;
    }
}
