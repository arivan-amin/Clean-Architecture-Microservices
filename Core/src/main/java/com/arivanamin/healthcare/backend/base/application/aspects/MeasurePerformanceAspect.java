package com.arivanamin.healthcare.backend.base.application.aspects;

import com.arivanamin.healthcare.backend.base.domain.aspects.PerformanceTimer;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.arivanamin.healthcare.backend.base.domain.aspects.ExecuteAndLogPerformance.executeThrowable;
import static com.arivanamin.healthcare.backend.base.domain.config.CoreApplicationConfig.BASE_PACKAGE;

@Aspect
@Component
@Slf4j
class MeasurePerformanceAspect {
    
    @Around ("@annotation(" + BASE_PACKAGE + ".base.domain.aspects.LogExecutionTime)")
    public Object logExecutionTimeOfMethod (ProceedingJoinPoint joinPoint) throws Throwable {
        logMethodNameAndParameters(joinPoint);
        
        PerformanceTimer timer = PerformanceTimer.newInstance();
        
        Object result;
        try {
            timer.startTimer();
            result = executeThrowable(joinPoint::proceed);
        }
        finally {
            stopTimerAndLogExecutionDuration(joinPoint, timer);
        }
        return result;
    }
    
    private void logMethodNameAndParameters (JoinPoint joinPoint) {
        List<Object> args = List.of(joinPoint.getArgs());
        log.info("Called method: {}, with parameters: {}", getMethodName(joinPoint), args);
    }
    
    private void stopTimerAndLogExecutionDuration (JoinPoint joinPoint, PerformanceTimer timer) {
        timer.stopTimer();
        timer.logMethodPerformance(getMethodName(joinPoint));
    }
    
    private String getMethodName (JoinPoint joinPoint) {
        return joinPoint.getSignature()
            .toString();
    }
}
