package com.arivanamin.healthcare.backend.base.application.aspects;

import com.arivanamin.healthcare.backend.base.domain.aspects.ExecuteAndLogPerformance;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
class SpringSchedulerLoggingAspect {
    
    @Around ("@annotation(org.springframework.scheduling.annotation.Scheduled)")
    public Object logScheduler (ProceedingJoinPoint joinPoint) throws Throwable {
        return ExecuteAndLogPerformance.executeThrowable(joinPoint::proceed);
    }
    
    private String initJobName (JoinPoint joinPoint) {
        final String classPath = joinPoint.getSignature()
            .getDeclaringTypeName();
        return "The scheduled task: %s".formatted(classPath);
    }
}
