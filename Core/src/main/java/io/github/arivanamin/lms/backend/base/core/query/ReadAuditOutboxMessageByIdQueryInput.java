package io.github.arivanamin.lms.backend.base.core.query;

import lombok.Value;

import java.util.UUID;

@Value
public class ReadAuditOutboxMessageByIdQueryInput {

    UUID id;
}
