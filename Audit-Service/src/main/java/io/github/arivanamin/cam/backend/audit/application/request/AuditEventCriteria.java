package io.github.arivanamin.cam.backend.audit.application.request;

import io.github.arivanamin.cam.backend.base.domain.audit.AuditEvent;
import lombok.Value;
import org.modelmapper.ModelMapper;

@Value
public class AuditEventCriteria {

    String serviceName;
    String location;
    String action;
    String data;

    public AuditEvent toDomain () {
        return new ModelMapper().map(this, AuditEvent.class);
    }
}
