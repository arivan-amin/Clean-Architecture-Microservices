package io.github.arivanamin.lms.backend.audit.storage;

import io.github.arivanamin.lms.backend.base.core.audit.AuditEvent;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

import static io.github.arivanamin.lms.backend.base.core.dates.TimestampHelper.toLocalDateTime;
import static io.github.arivanamin.lms.backend.base.core.dates.TimestampHelper.toTimestampInMilliseconds;

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
    LocalDateTime recordedAt;

    @Positive
    long duration;

    @NotBlank
    String response;

    public static JpaAuditEvent fromDomain (AuditEvent domain) {
        return new JpaAuditEvent(domain.getId(), domain.getServiceName(), domain.getLocation(),
            domain.getAction(), domain.getData(), toLocalDateTime(domain.getTimestamp()),
            domain.getDuration(), domain.getResponse());
    }

    public AuditEvent toDomain () {
        return new AuditEvent(id, serviceName, location, action, data,
            toTimestampInMilliseconds(recordedAt), duration, response);
    }
}
