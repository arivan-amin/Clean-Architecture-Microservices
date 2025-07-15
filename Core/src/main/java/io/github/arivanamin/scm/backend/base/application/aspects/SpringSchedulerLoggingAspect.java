package io.github.arivanamin.scm.backend.base.application.aspects;

import io.github.arivanamin.scm.backend.base.domain.aspects.PerformanceTimer;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
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

    private void logScheduledTaskDetails (ProceedingJoinPoint joinPoint) {
        log.info("Running = {}", getJobName(joinPoint));
    }

    private String getJobName (ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = signature.getDeclaringTypeName();
        String methodName = signature.getMethod()
            .getName();
        return "Scheduled task: %s:%s".formatted(className, methodName);
    }

    private void stopTimerAndLogExecutionDuration (ProceedingJoinPoint joinPoint,
                                                   PerformanceTimer timer) {
        timer.stopTimer();
        timer.logMethodPerformance(getJobName(joinPoint));
    }
}
