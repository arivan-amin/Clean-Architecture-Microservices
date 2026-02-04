package io.github.arivanamin.lms.backend.core.domain.outbox;

import io.github.arivanamin.lms.backend.core.domain.audit.AuditEvent;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults (level = AccessLevel.PRIVATE)
public class AuditOutboxMessage {

    UUID id;
    String serviceName;
    String location;
    String action;
    String data;
    long timestamp;
    long duration;
    String response;
    OutboxMessageStatus status;

    public static AuditOutboxMessage fromDomain (AuditEvent event) {
        return new AuditOutboxMessage(event.getId(), event.getServiceName(), event.getLocation(),
            event.getAction(), event.getData(), event.getTimestamp(), event.getDuration(),
            event.getResponse(), OutboxMessageStatus.PENDING);
    }

    public AuditEvent toDomain () {
        return new AuditEvent(id, serviceName, location, action, data, timestamp, duration,
            response);
    }
}
