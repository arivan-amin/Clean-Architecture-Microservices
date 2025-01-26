package com.arivanamin.healthcare.backend.base.domain.aspects;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PerformanceTimer {
    
    public static final int MILLISECOND_DIVISOR = 1_000_000;
    
    long start;
    long end;
    
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
