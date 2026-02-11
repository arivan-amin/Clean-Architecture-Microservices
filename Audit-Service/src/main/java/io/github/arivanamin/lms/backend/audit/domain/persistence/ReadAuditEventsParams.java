package io.github.arivanamin.lms.backend.audit.domain.persistence;

import io.github.arivanamin.lms.backend.core.domain.pagination.PaginationCriteria;
import lombok.Value;

import java.time.Instant;

@Value
public class ReadAuditEventsParams {

    Instant startDate;
    Instant endDate;
    PaginationCriteria criteria;
}
