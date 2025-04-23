package io.github.arivanamin.scm.backend.common.storage.audit;

import io.github.arivanamin.scm.backend.base.domain.audit.AuditEvent;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import org.modelmapper.ModelMapper;

import java.util.UUID;

@Entity
@Table (name = "api_audit_events_outbox")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class JpaAuditEventOutboxMessage {

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

    @PastOrPresent
    private long timestamp;

    @Positive
    private long duration;

    @NotBlank
    private String response;

    public static JpaAuditEventOutboxMessage fromDomain (AuditEvent event) {
        return new ModelMapper().map(event, JpaAuditEventOutboxMessage.class);
    }

    public AuditEvent toDomain () {
        return new ModelMapper().map(this, AuditEvent.class);
    }
}
