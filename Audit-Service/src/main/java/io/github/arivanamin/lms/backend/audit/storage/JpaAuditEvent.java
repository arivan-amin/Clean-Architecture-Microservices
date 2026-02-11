package io.github.arivanamin.lms.backend.audit.storage;

import io.github.arivanamin.lms.backend.core.domain.audit.AuditEvent;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table (name = "audit_events")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults (level = AccessLevel.PRIVATE)
@ToString
public class JpaAuditEvent {

    @Id
    UUID id;

    @NotBlank
    String serviceName;

    @NotBlank
    String location;

    @NotBlank
    String action;

    @NotBlank
    String data;

    @PastOrPresent
    @Column (nullable = false)
    Instant recordedAt;

    @NotNull
    Duration duration;

    @NotBlank
    String response;

    public static JpaAuditEvent fromDomain (AuditEvent domain) {
        return new JpaAuditEvent(domain.getId(), domain.getServiceName(), domain.getLocation(),
            domain.getAction(), domain.getData(), domain.getRecordedAt(), domain.getDuration(),
            domain.getResponse());
    }

    public AuditEvent toDomain () {
        return new AuditEvent(id, serviceName, location, action, data, recordedAt, duration,
            response);
    }
}
