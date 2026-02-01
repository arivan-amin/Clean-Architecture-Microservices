package io.github.arivanamin.lms.backend.audit.core.query.readbyid;

import lombok.Value;

import java.util.UUID;

@Value
public class ReadAuditEventByIdQueryInput {

    UUID id;
}
