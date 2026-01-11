package io.github.arivanamin.scm.backend.base.core.outbox;

import io.github.arivanamin.scm.backend.base.core.audit.AuditEvent;
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
