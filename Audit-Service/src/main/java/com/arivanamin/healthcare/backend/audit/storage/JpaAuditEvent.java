package com.arivanamin.healthcare.backend.audit.storage;

import com.arivanamin.healthcare.backend.base.domain.audit.AuditEvent;
import jakarta.persistence.Id;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

import static com.arivanamin.healthcare.backend.base.domain.dates.TimestampHelper.toLocalDateTime;
import static com.arivanamin.healthcare.backend.base.domain.dates.TimestampHelper.toTimestampInMilliseconds;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class JpaAuditEvent {
    
    @Id
    String id;
    private String serviceName;
    private String location;
    private String action;
    private String data;
    private LocalDateTime recordedAt;
    private long duration;
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
