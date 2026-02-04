package io.github.arivanamin.lms.backend.core.domain.query;

import lombok.Value;

import java.util.UUID;

@Value
public class ReadAuditOutboxMessageByIdQueryInput {

    UUID id;
}
