package io.github.arivanamin.lms.backend.audit.domain.query.readbyspec;

import io.github.arivanamin.cinemayan.backend.core.domain.pagination.PaginationCriteria;
import lombok.Value;

import java.time.Instant;

@Value
public class ReadAuditEventsInput {

    Instant startDate;
    Instant endDate;
    PaginationCriteria criteria;
}
