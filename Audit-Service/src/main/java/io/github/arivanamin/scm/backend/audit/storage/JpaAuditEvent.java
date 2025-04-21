package io.github.arivanamin.scm.backend.audit.storage;

import io.github.arivanamin.scm.backend.base.domain.audit.AuditEvent;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.UUID;

import static io.github.arivanamin.scm.backend.base.domain.dates.TimestampHelper.toLocalDateTime;
import static io.github.arivanamin.scm.backend.base.domain.dates.TimestampHelper.toTimestampInMilliseconds;

@Entity
@Table (name = "api_audit_events")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class JpaAuditEvent {

    @Id
    @UuidGenerator
    UUID id;

    @NotBlank
    private String serviceName;

    @NotBlank
    private String location;

    @NotBlank
    private String action;

    @NotBlank
    private String data;

    @Past
    private LocalDateTime recordedAt;

    @Positive
    private long duration;

    @NotBlank
    private String response;

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
