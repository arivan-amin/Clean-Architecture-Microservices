package io.github.arivanamin.scm.backend.base.domain.aspects;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
@Slf4j
public final class PerformanceTimer {

    public static final int MILLISECOND_DIVISOR = 1_000_000;

    private long start;
    private long end;

    public static PerformanceTimer newInstance () {
        return new PerformanceTimer();
    }

    public void startTimer () {
        start = System.nanoTime();
    }

    public void stopTimer () {
        end = System.nanoTime();
    }

    public void logMethodPerformance (String methodName) {
        log.info("execution of {} took {}ms", methodName, getDuration());
    }

    public long getDuration () {
        return (end - start) / MILLISECOND_DIVISOR;
    }
}
