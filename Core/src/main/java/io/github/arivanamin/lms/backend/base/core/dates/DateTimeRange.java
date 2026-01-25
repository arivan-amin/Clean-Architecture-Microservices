package io.github.arivanamin.lms.backend.base.core.dates;

import lombok.Value;

import java.time.LocalDateTime;
import java.util.Objects;

import static io.github.arivanamin.lms.backend.base.core.dates.TimestampHelper.toLocalDateTime;

@Value
public class DateTimeRange {

    LocalDateTime start;
    LocalDateTime end;

    private DateTimeRange (LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    public static DateTimeRange of (long startTimestamp, long endTimestamp) {
        return of(toLocalDateTime(startTimestamp), toLocalDateTime(endTimestamp));
    }

    public static DateTimeRange of (LocalDateTime start, LocalDateTime end) {
        areParametersValid(start, end);

        if (isRangeIncorrect(start, end)) {
            throw new IllegalArgumentException("End must not be before start");
        }

        return new DateTimeRange(start, end);
    }

    private static void areParametersValid (LocalDateTime start, LocalDateTime end) {
        Objects.requireNonNull(start, "Start must not be null");
        Objects.requireNonNull(end, "End must not be null");
    }

    private static boolean isRangeIncorrect (LocalDateTime start, LocalDateTime end) {
        return end.isBefore(start);
    }
}
