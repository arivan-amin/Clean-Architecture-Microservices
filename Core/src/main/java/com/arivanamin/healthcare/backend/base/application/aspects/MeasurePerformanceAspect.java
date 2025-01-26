package com.arivanamin.healthcare.backend.base.application.aspects;

import lombok.extern.slf4j.Slf4j;
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
        List<Object> args = List.of(joinPoint.getArgs());
        
        log.info("Called method: {}, with parameters: {}", joinPoint.getSignature(), args);
        
        String methodName = joinPoint.getSignature()
            .toShortString();
        return executeThrowable(joinPoint::proceed);
    }
}
