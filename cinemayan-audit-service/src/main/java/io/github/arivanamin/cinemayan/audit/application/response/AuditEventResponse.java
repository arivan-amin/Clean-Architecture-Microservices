package io.github.arivanamin.cinemayan.audit.application.response;

import io.github.arivanamin.cinemayan.core.domain.audit.AuditEvent;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

import static io.github.arivanamin.cinemayan.core.domain.util.MappingUtility.mapIfNotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditEventResponse {

    UUID id;
    String serviceName;
    String location;
    String action;
    String data;
    Long recordedAt;
    String duration;
    String response;

    public static AuditEventResponse of (AuditEvent event) {
        AuditEventResponse response = new AuditEventResponse();
        response.setId(event.getId());
        response.setServiceName(event.getServiceName());
        response.setLocation(event.getLocation());
        response.setAction(event.getAction());
        response.setData(event.getData());
        response.setRecordedAt(mapIfNotNull(event.getRecordedAt(), Instant::toEpochMilli));
        response.setDuration("%sms".formatted(event.getDuration()
            .toMillis()));
        response.setResponse(event.getResponse());
        return response;
    }
}
