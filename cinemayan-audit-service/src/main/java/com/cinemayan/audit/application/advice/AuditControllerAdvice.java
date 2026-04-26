package com.cinemayan.audit.application.advice;

import com.cinemayan.audit.domain.exception.AuditEventNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.Clock;
import java.time.Instant;

import static io.github.arivanamin.cinemayan.core.application.advice.ProblemDetailCategories.RESOURCE_NOT_FOUND;
import static io.github.arivanamin.cinemayan.core.application.advice.ProblemDetailExceptionUrls.RUNTIME_EXCEPTION_URL;
import static io.github.arivanamin.cinemayan.core.application.advice.ProblemDetailProperties.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public final class AuditControllerAdvice {

    private final Clock clock;

    @ResponseStatus (NOT_FOUND)
    @ExceptionHandler (AuditEventNotFoundException.class)
    ProblemDetail handleAuditEventNotFound (AuditEventNotFoundException exception) {
        log.error(exception.getMessage(), exception);

        ProblemDetail detail = ProblemDetail.forStatusAndDetail(NOT_FOUND, exception.getMessage());
        detail.setTitle("Audit Event Not Found");
        detail.setType(URI.create(RUNTIME_EXCEPTION_URL));
        detail.setProperty(CATEGORY, RESOURCE_NOT_FOUND);
        detail.setProperty(TIMESTAMP, Instant.now(clock));
        detail.setProperty(TRACE_ID_KEY, MDC.get(TRACE_ID_VALUE));
        detail.setProperty(SPAN_ID_KEY, MDC.get(SPAN_ID_VALUE));
        return detail;
    }
}
