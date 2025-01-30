package com.arivanamin.healthcare.backend.api.gateway.application.advice;

import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.resource.NoResourceFoundException;

import java.net.URI;
import java.time.Instant;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ProblemDetail.forStatusAndDetail;

@RestControllerAdvice
class ApiGatewayAdvice {

    @ExceptionHandler (NotFoundException.class)
    public ProblemDetail handleRateLimitingException (NotFoundException exception) {
        ProblemDetail detail = forStatusAndDetail(TOO_MANY_REQUESTS, exception.getMessage());
        detail.setTitle("Rate Limit Exceeded");
        detail.setType(URI.create(
            "https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework" +
                "/web/server/ResponseStatusException.html"));
        detail.setProperty("errorCategory", "You have exceeded the rate limit, try again later");
        detail.setProperty("timestamp", Instant.now());
        detail.setDetail(exception.getMessage());
        return detail;
    }

    @ExceptionHandler (NoResourceFoundException.class)
    ProblemDetail handleResourceNotFound (NoResourceFoundException exception) {
        ProblemDetail detail = forStatusAndDetail(NOT_FOUND, exception.getMessage());
        detail.setTitle("Resource Not Found");
        detail.setType(URI.create(
            "https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework" +
                "/web/reactive/resource/NoResourceFoundException.html"));
        detail.setProperty("errorCategory", "Resource Not Found");
        detail.setProperty("timestamp", Instant.now());
        detail.setDetail(exception.getMessage());
        return detail;
    }

    @ExceptionHandler (Exception.class)
    ProblemDetail handleGeneralExceptions (Exception exception) {
        ProblemDetail detail = forStatusAndDetail(INTERNAL_SERVER_ERROR, exception.getMessage());
        detail.setTitle("General Error");
        detail.setType(URI.create(
            "https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/Exception" +
                ".html"));
        detail.setProperty("errorCategory", "Internal Error");
        detail.setProperty("timestamp", Instant.now());
        detail.setDetail(exception.getMessage());
        return detail;
    }
}
