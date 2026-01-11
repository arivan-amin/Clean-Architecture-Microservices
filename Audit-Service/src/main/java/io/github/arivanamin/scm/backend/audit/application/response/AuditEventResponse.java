package io.github.arivanamin.scm.backend.audit.application.response;

import io.github.arivanamin.scm.backend.base.core.audit.AuditEvent;
import io.github.arivanamin.scm.backend.base.core.dates.TimestampHelper;
import lombok.*;

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
        AuditEventResponse response = new AuditEventResponse();
        response.setId(event.getId());
        response.setServiceName(event.getServiceName());
        response.setLocation(event.getLocation());
        response.setAction(event.getAction());
        response.setData(event.getData());
        response.setRecordedAt(TimestampHelper.toLocalDateTime(event.getTimestamp()));
        response.setDuration("%dms".formatted(event.getDuration()));
        response.setResponse(event.getResponse());
        return response;
    }
}
