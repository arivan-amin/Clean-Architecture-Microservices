package io.github.arivanamin.scm.backend.audit.application.response;

import io.github.arivanamin.scm.backend.base.core.audit.AuditEvent;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditEventResponse {

    private UUID id;
    private String serviceName;
    private String location;
    private String action;
    private String data;
    private LocalDateTime recordedAt;
    private String duration;
    private String response;

    public static AuditEventResponse of (AuditEvent event) {
        AuditEventResponse response = new ModelMapper().map(event, AuditEventResponse.class);
        response.setDuration("%dms".formatted(event.getDuration()));
        return response;
    }
}
