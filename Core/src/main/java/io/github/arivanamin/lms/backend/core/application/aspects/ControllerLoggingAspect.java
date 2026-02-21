package io.github.arivanamin.lms.backend.core.application.aspects;

import io.github.arivanamin.lms.backend.core.application.audit.AuditDataExtractor;
import io.github.arivanamin.lms.backend.core.domain.audit.AuditEvent;
import io.github.arivanamin.lms.backend.core.domain.command.CreateAuditOutboxMessageCommand;
import io.github.arivanamin.lms.backend.core.domain.command.CreateAuditOutboxMessageCommandInput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.*;
import java.util.Arrays;

import static io.github.arivanamin.lms.backend.core.domain.aspects.ExecutionWrapper.executeThrowable;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
class ControllerLoggingAspect {

    private final CreateAuditOutboxMessageCommand command;
    private final AuditDataExtractor dataExtractor;
    private final Clock clock;

    @Around ("""
            @annotation(org.springframework.web.bind.annotation.GetMapping)
            or @annotation(org.springframework.web.bind.annotation.PostMapping)
            or @annotation(org.springframework.web.bind.annotation.PutMapping)
            or @annotation(org.springframework.web.bind.annotation.DeleteMapping)
            or @annotation(org.springframework.web.bind.annotation.PatchMapping)
        """)
    public Object logEndpoint (ProceedingJoinPoint joinPoint) throws Throwable {
        logIncomingRequestDetails(joinPoint);

        Instant start = Instant.now(clock);
        Object result = null;
        try {
            result = executeThrowable(joinPoint::proceed);
        }
        catch (RuntimeException exception) {
            result = "Error: " + exception.getMessage();
        }
        finally {
            Duration duration = Duration.between(start, Instant.now());
            logExecutionDuration(joinPoint, duration);
            extractAuditEventDetailsAndSaveToStorage(joinPoint, result, duration);
        }
        return result;
    }

    private void logIncomingRequestDetails (JoinPoint joinPoint) {
        log.info("Incoming request to: {}, with parameters: {}", joinPoint.getSignature(),
            Arrays.deepToString(joinPoint.getArgs()));
    }

    private void logExecutionDuration (ProceedingJoinPoint joinPoint, Duration duration) {
        log.info("execution of {} took {}ms", getMethodName(joinPoint), duration.toMillis());
    }

    private void extractAuditEventDetailsAndSaveToStorage (ProceedingJoinPoint joinPoint,
                                                           Object result, Duration duration) {
        AuditEvent event = dataExtractor.extractAuditData(joinPoint, result, duration);
        command.execute(new CreateAuditOutboxMessageCommandInput(event));
    }

    private String getMethodName (JoinPoint joinPoint) {
        return "Controller endpoint %s ".formatted(joinPoint.getSignature());
    }
}
