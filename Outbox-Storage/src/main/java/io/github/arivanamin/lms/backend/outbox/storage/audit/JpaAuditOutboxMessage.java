package io.github.arivanamin.lms.backend.outbox.storage.audit;

import io.github.arivanamin.lms.backend.base.core.outbox.AuditOutboxMessage;
import io.github.arivanamin.lms.backend.base.core.outbox.OutboxMessageStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table (name = "api_audit_events_outbox")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults (level = AccessLevel.PRIVATE)
@ToString
public class JpaAuditOutboxMessage {

    @Id
    @UuidGenerator
    UUID id;

    @NotBlank
    String serviceName;

    @NotBlank
    String location;

    @NotBlank
    String action;

    @NotBlank
    String data;

    @Positive
    long timestamp;

    @Positive
    long duration;

    @NotBlank
    String response;

    @NotNull
    OutboxMessageStatus status;

    public static JpaAuditOutboxMessage fromDomain (AuditOutboxMessage domain) {
        return new JpaAuditOutboxMessage(domain.getId(), domain.getServiceName(),
            domain.getLocation(), domain.getAction(), domain.getData(), domain.getTimestamp(),
            domain.getDuration(), domain.getResponse(), domain.getStatus());
    }

    public AuditOutboxMessage toDomain () {
        return new AuditOutboxMessage(id, serviceName, location, action, data, timestamp, duration,
            response, status);
    }
}
