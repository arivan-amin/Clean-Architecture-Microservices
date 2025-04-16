package io.github.arivanamin.cam.backend.audit.application.response;

import io.github.arivanamin.cam.backend.base.domain.audit.AuditEvent;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

import static io.github.arivanamin.cam.backend.base.domain.dates.TimestampHelper.toLocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditEventResponse {

    private String id;
    private String serviceName;
    private String location;
    private String action;
    private String data;
    private LocalDateTime creationDate;
    private String duration;
    private String response;

    public static AuditEventResponse of (AuditEvent event) {
        AuditEventResponse response = new ModelMapper().map(event, AuditEventResponse.class);
        response.setCreationDate(toLocalDateTime(event.getTimestamp()));
        response.setDuration(event.getDuration() + "ms");
        return response;
    }
}
