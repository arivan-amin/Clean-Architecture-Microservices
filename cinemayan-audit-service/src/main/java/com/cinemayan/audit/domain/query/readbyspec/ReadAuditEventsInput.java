package com.cinemayan.audit.domain.query.readbyspec;

import io.github.arivanamin.cinemayan.core.domain.pagination.PaginationCriteria;
import lombok.Value;

import java.time.Instant;

@Value
public class ReadAuditEventsInput {

    Instant startDate;
    Instant endDate;
    PaginationCriteria criteria;
}
