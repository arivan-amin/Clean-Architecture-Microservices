package io.github.arivanamin.lms.backend.audit.application.response;

import io.github.arivanamin.lms.backend.core.domain.audit.AuditEvent;
import lombok.*;

import java.time.Instant;
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
    private Instant recordedAt;
    private String duration;
    private String response;

    public static AuditEventResponse of (AuditEvent event) {
        AuditEventResponse response = new AuditEventResponse();
        response.setId(event.getId());
        response.setServiceName(event.getServiceName());
        response.setLocation(event.getLocation());
        response.setAction(event.getAction());
        response.setData(event.getData());
        response.setRecordedAt(event.getRecordedAt());
        response.setDuration("%sms".formatted(event.getDuration()));
        response.setResponse(event.getResponse());
        return response;
    }
}
