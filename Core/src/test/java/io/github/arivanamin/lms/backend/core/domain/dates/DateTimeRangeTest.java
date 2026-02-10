package io.github.arivanamin.lms.backend.core.domain.dates;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
class DateTimeRangeTest {

    @Test
    void shouldNotThrowAnyExceptionForValidDates () {
        // given
        LocalDateTime start = LocalDateTime.now()
            .minusDays(5);
        LocalDateTime end = LocalDateTime.now()
            .minusDays(1);

        // when & then
        assertDoesNotThrow(() -> DateTimeRange.of(start, end));
    }

    @Test
    void shouldNotThrowAnyExceptionForValidTimestamps () {
        // given
        long start = TimestampHelper.toTimestampInMilliseconds(LocalDateTime.now()
            .minusDays(5));
        long end = TimestampHelper.toTimestampInMilliseconds(LocalDateTime.now()
            .minusDays(1));

        // when & then
        assertDoesNotThrow(() -> DateTimeRange.of(start, end));
    }

    @Test
    void shouldThrowExceptionWhenStartIsAfterEnd () {
        // given
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now()
            .minusDays(1);

        // when & then
        assertThrows(IllegalArgumentException.class, () -> DateTimeRange.of(start, end));
    }

    @Test
    void shouldThrowExceptionWhenStartIsNull () {
        // given
        LocalDateTime end = LocalDateTime.now()
            .minusDays(1);

        // when & then
        assertThrows(NullPointerException.class, () -> DateTimeRange.of(null, end));
    }

    @Test
    void shouldThrowExceptionWhenEndIsNull () {
        // given
        LocalDateTime start = LocalDateTime.now()
            .minusDays(3);

        // when & then
        assertThrows(NullPointerException.class, () -> DateTimeRange.of(start, null));
    }
}
