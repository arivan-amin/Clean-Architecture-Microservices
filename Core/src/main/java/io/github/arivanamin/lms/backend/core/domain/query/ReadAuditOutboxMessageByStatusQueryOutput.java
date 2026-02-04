package io.github.arivanamin.lms.backend.core.domain.query;

import io.github.arivanamin.lms.backend.core.domain.audit.AuditEvent;
import lombok.Value;

import java.util.List;

@Value
public class ReadAuditOutboxMessageByStatusQueryOutput {

    List<AuditEvent> events;
}
