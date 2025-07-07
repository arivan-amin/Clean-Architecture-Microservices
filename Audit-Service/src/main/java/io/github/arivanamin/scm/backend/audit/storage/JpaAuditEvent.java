package io.github.arivanamin.scm.backend.audit.storage;

import io.github.arivanamin.scm.backend.base.domain.audit.AuditEvent;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.UUID;

import static io.github.arivanamin.scm.backend.base.domain.dates.TimestampHelper.toLocalDateTime;
import static io.github.arivanamin.scm.backend.base.domain.dates.TimestampHelper.toTimestampInMilliseconds;

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

    @PastOrPresent
    LocalDateTime recordedAt;

    @Positive
    long duration;

    @NotBlank
    String response;

    public static JpaAuditEvent fromDomain (AuditEvent event) {
        JpaAuditEvent jpaEvent = new ModelMapper().map(event, JpaAuditEvent.class);
        jpaEvent.setRecordedAt(toLocalDateTime(event.getTimestamp()));
        return jpaEvent;
    }

    public AuditEvent toDomain () {
        AuditEvent event = new ModelMapper().map(this, AuditEvent.class);
        event.setTimestamp(toTimestampInMilliseconds(recordedAt));
        return event;
    }
}
