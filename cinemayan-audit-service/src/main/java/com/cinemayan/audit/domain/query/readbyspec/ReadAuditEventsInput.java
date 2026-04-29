package com.cinemayan.audit.domain.query.readbyspec;

import com.cinemayan.core.domain.pagination.PaginationCriteria;
import lombok.Value;

import java.time.Instant;

@Value
public class ReadAuditEventsInput {

    Instant startDate;
    Instant endDate;
    PaginationCriteria criteria;
}
