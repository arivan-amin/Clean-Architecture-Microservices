package io.github.arivanamin.lms.backend.base.core.query;

import io.github.arivanamin.lms.backend.base.core.audit.AuditEvent;
import lombok.Value;

import java.util.List;

@Value
public class ReadAuditOutboxMessageByStatusQueryOutput {

    List<AuditEvent> events;
}
