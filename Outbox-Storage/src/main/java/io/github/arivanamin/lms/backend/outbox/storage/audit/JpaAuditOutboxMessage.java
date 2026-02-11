package io.github.arivanamin.lms.backend.outbox.storage.audit;

import io.github.arivanamin.lms.backend.core.domain.outbox.AuditOutboxMessage;
import io.github.arivanamin.lms.backend.core.domain.outbox.OutboxMessageStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

import static jakarta.persistence.EnumType.STRING;

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

    @NotNull
    Instant recordedAt;

    @NotNull
    Duration duration;

    @NotBlank
    String response;

    @Enumerated (STRING)
    @NotNull
    OutboxMessageStatus status;

    public static JpaAuditOutboxMessage fromDomain (AuditOutboxMessage domain) {
        return new JpaAuditOutboxMessage(domain.getId(), domain.getServiceName(),
            domain.getLocation(), domain.getAction(), domain.getData(), domain.getRecordedAt(),
            domain.getDuration(), domain.getResponse(), domain.getStatus());
    }

    public AuditOutboxMessage toDomain () {
        return new AuditOutboxMessage(id, serviceName, location, action, data, recordedAt, duration,
            response, status);
    }
}
