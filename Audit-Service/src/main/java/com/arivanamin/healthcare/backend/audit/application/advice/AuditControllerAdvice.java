package com.arivanamin.healthcare.backend.audit.application.advice;

import com.arivanamin.healthcare.backend.audit.core.exception.AuditEventNotFoundException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ProblemDetail.forStatusAndDetail;

@RestControllerAdvice
@NoArgsConstructor (access = AccessLevel.PRIVATE)
@Slf4j
public final class AuditControllerAdvice {

    @ExceptionHandler (AuditEventNotFoundException.class)
    ProblemDetail handleAuditEventNotFound (AuditEventNotFoundException exception) {
        ProblemDetail detail = forStatusAndDetail(NOT_FOUND, exception.getMessage());
        detail.setTitle("Bad Request, Audit Event not found");
        detail.setType(URI.create("https://docs.oracle.com/en/java/javase/21/docs/api/java" +
            ".base/java/lang/RuntimeException.html"));
        detail.setProperty("errorCategory", "Resource not found");
        detail.setProperty("timestamp", Instant.now());
        detail.setDetail(exception.getMessage());
        log.error("AuditEventNotFoundException advice", exception);
        return detail;
    }
}
