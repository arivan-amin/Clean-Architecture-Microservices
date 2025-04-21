package io.github.arivanamin.scm.backend.base.application.aspects;

import io.github.arivanamin.scm.backend.base.application.audit.AuditDataExtractor;
import io.github.arivanamin.scm.backend.base.domain.aspects.PerformanceTimer;
import io.github.arivanamin.scm.backend.base.domain.audit.AuditEvent;
import io.github.arivanamin.scm.backend.base.domain.audit.AuditEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;

import static io.github.arivanamin.scm.backend.base.domain.aspects.ExecuteAndLogPerformance.executeThrowable;
import static io.github.arivanamin.scm.backend.base.domain.topics.AuditTopics.API_AUDIT_TOPIC;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
class ControllerLoggingAspect {

    private final AuditEventPublisher publisher;
    private final AuditDataExtractor dataExtractor;

    @Around ("""
            @annotation(org.springframework.web.bind.annotation.GetMapping)
            or @annotation(org.springframework.web.bind.annotation.PostMapping)
            or @annotation(org.springframework.web.bind.annotation.PutMapping)
            or @annotation(org.springframework.web.bind.annotation.DeleteMapping)
            or @annotation(org.springframework.web.bind.annotation.PatchMapping)
        """)
    public Object logEndpoint (ProceedingJoinPoint joinPoint) throws Throwable {
        logIncomingRequestDetails(joinPoint);
        PerformanceTimer timer = PerformanceTimer.newInstance();

        Object result = null;
        try {
            timer.startTimer();
            result = executeThrowable(joinPoint::proceed);
        }
        catch (RuntimeException exception) {
            result = "Error: " + exception.getMessage();
            throw exception;
        }
        finally {
            stopTimerAndLogExecutionDuration(joinPoint, timer);
            sendAuditEventThroughPublisher(joinPoint, result, timer.getDuration());
        }
        return result;
    }

    private static void logIncomingRequestDetails (JoinPoint joinPoint) {
        log.info("Incoming request to: {}, with parameters: {}", joinPoint.getSignature(),
            List.of(joinPoint.getArgs()));
    }

    private static void stopTimerAndLogExecutionDuration (JoinPoint joinPoint,
                                                          PerformanceTimer timer) {
        timer.stopTimer();
        timer.logMethodPerformance(getMethodName(joinPoint));
    }

    private void sendAuditEventThroughPublisher (ProceedingJoinPoint joinPoint, Object result,
                                                 long duration) {
        AuditEvent event = dataExtractor.extractAuditData(joinPoint, result, duration);
        publisher.sendAuditLog(API_AUDIT_TOPIC, event);
    }

    private static String getMethodName (JoinPoint joinPoint) {
        return "Controller endpoint %s ".formatted(joinPoint.getSignature());
    }
}
