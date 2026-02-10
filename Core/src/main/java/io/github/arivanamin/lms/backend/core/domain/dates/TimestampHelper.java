package io.github.arivanamin.lms.backend.core.domain.dates;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.*;

import static io.github.arivanamin.lms.backend.core.domain.config.CoreApplicationConfig.DEFAULT_ZONE_ID;
import static java.time.LocalDateTime.ofInstant;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@NoArgsConstructor (access = PRIVATE)
public final class TimestampHelper {

    public static LocalDate toLocalDate (Long timestamp) {
        if (timestamp != null) {
            return toLocalDateTime(timestamp).toLocalDate();
        }
        return null;
    }

    public static LocalDateTime toLocalDateTime (Long timestamp) {
        if (timestamp != null) {
            return ofInstant(Instant.ofEpochMilli(timestamp), DEFAULT_ZONE_ID);
        }
        return null;
    }

    public static Long toTimestampInMilliseconds (LocalDateTime dateTime) {
        return dateTime.atZone(DEFAULT_ZONE_ID)
            .toInstant()
            .toEpochMilli();
    }
}
