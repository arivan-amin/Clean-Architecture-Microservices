package io.github.arivanamin.scm.backend.base.domain.outbox;

import io.github.arivanamin.scm.backend.base.domain.audit.AuditEvent;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;

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
        return new ModelMapper().map(event, AuditOutboxMessage.class);
    }

    public AuditEvent toDomain () {
        return new ModelMapper().map(this, AuditEvent.class);
    }
}
