package io.github.arivanamin.lms.backend.audit.core.util;

import java.time.LocalDateTime;

import static io.github.arivanamin.lms.backend.base.core.dates.TimestampHelper.toLocalDateTime;

public record AuditPeriod(LocalDateTime start, LocalDateTime end) {

    public static AuditPeriod of (long startTimestamp, long endTimestamp) {
        return new AuditPeriod(toLocalDateTime(startTimestamp), toLocalDateTime(endTimestamp));
    }
}
