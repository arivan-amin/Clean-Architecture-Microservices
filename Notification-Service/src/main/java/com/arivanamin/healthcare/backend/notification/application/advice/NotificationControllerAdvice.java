package com.arivanamin.healthcare.backend.notification.application.advice;

import com.arivanamin.healthcare.backend.notification.core.exception.NotificationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;

import static com.arivanamin.healthcare.backend.base.application.advice.ProblemDetailCategories.RESOURCE_NOT_FOUND;
import static com.arivanamin.healthcare.backend.base.application.advice.ProblemDetailProperties.CATEGORY;
import static com.arivanamin.healthcare.backend.base.application.advice.ProblemDetailProperties.TIMESTAMP;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ProblemDetail.forStatusAndDetail;

@RestControllerAdvice
@NoArgsConstructor (access = AccessLevel.PRIVATE)
@Slf4j
public final class NotificationControllerAdvice {

    @ExceptionHandler (NotificationException.class)
    ProblemDetail handleAuditEventNotFound (NotificationException exception) {
        ProblemDetail detail = forStatusAndDetail(NOT_FOUND, exception.getMessage());
        detail.setTitle("Audit Event Not Found");
        detail.setType(URI.create("https://docs.oracle.com/en/java/javase/21/docs/api/java" +
            ".base/java/lang/RuntimeException.html"));
        detail.setProperty(CATEGORY, RESOURCE_NOT_FOUND);
        detail.setProperty(TIMESTAMP, Instant.now());
        log.error(exception.getMessage(), exception);
        return detail;
    }
}
