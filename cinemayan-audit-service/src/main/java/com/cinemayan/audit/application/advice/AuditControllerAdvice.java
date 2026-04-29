package com.cinemayan.audit.application.advice;

import com.cinemayan.audit.domain.exception.AuditEventNotFoundException;
import com.cinemayan.core.application.advice.ProblemDetailFactory;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;

import static com.cinemayan.core.application.advice.ProblemDetailCategories.RESOURCE_NOT_FOUND;
import static com.cinemayan.core.application.advice.ProblemDetailExceptionUrls.RUNTIME_EXCEPTION_URL;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public final class AuditControllerAdvice {

    private final ProblemDetailFactory factory;

    @ResponseStatus (NOT_FOUND)
    @ExceptionHandler (AuditEventNotFoundException.class)
    ProblemDetail handleAuditEventNotFound (AuditEventNotFoundException exception,
                                            HttpServletRequest request) {
        log.warn("Audit event not found Exception [eventID = {}, errorCode = {}, URI = {}]",
            exception.getEventId(), exception.getErrorCode(), request.getRequestURI());

        String title = "Resource Not Found";
        String detail = "Audit Event with ID: %s, Not Found".formatted(exception.getEventId());
        return factory.build(NOT_FOUND, title, detail, RESOURCE_NOT_FOUND, RUNTIME_EXCEPTION_URL);
    }
}
