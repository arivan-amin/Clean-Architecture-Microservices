package io.github.arivanamin.scm.backend.base.application.aspects;

import io.github.arivanamin.scm.backend.base.domain.aspects.PerformanceTimer;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import static io.github.arivanamin.scm.backend.base.domain.aspects.ExecuteAndLogPerformance.executeThrowable;

@Aspect
@Component
@Slf4j
class SpringSchedulerLoggingAspect {

    @Around ("@annotation(org.springframework.scheduling.annotation.Scheduled)")
    public Object logScheduler (ProceedingJoinPoint joinPoint) throws Throwable {
        logScheduledTaskDetails(joinPoint);

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

    private void logScheduledTaskDetails (JoinPoint joinPoint) {
        log.info("Running = {}", getJobName(joinPoint));
    }

    private void stopTimerAndLogExecutionDuration (JoinPoint joinPoint, PerformanceTimer timer) {
        timer.stopTimer();
        timer.logMethodPerformance(getJobName(joinPoint));
    }

    private String getJobName (JoinPoint joinPoint) {
        final String classPath = joinPoint.getSignature()
            .getDeclaringTypeName();
        return "Scheduled task: %s".formatted(classPath);
    }
}
