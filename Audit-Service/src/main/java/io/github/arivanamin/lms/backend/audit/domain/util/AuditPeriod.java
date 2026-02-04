package io.github.arivanamin.lms.backend.audit.domain.util;

import java.time.LocalDateTime;

import static io.github.arivanamin.lms.backend.core.domain.dates.TimestampHelper.toLocalDateTime;

public record AuditPeriod(LocalDateTime start, LocalDateTime end) {

    public static AuditPeriod of (long startTimestamp, long endTimestamp) {
        return new AuditPeriod(toLocalDateTime(startTimestamp), toLocalDateTime(endTimestamp));
    }
}
