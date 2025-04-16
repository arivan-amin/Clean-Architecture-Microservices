package io.github.arivanamin.cam.backend.base.application.advice;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.*;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.net.URI;
import java.time.Instant;

import static io.github.arivanamin.cam.backend.base.application.advice.ProblemDetailCategories.*;
import static io.github.arivanamin.cam.backend.base.application.advice.ProblemDetailProperties.CATEGORY;
import static io.github.arivanamin.cam.backend.base.application.advice.ProblemDetailProperties.TIMESTAMP;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ProblemDetail.forStatusAndDetail;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
@Slf4j
public final class CommonProblemDetails {

    public static final String SPRING_DOCS_URI =
        "https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework";

    public static ProblemDetail getResourceNotFoundProblemDetail (
        NoResourceFoundException exception) {
        ProblemDetail detail = forStatusAndDetail(NOT_FOUND, exception.getMessage());
        detail.setTitle("Requested Resource not found");
        detail.setType(URI.create(
            SPRING_DOCS_URI + "/web/servlet/resource/NoResourceFoundException.html" +
                ".base/java/lang/RuntimeException.html"));
        detail.setProperty(CATEGORY, RESOURCE_NOT_FOUND);
        detail.setProperty(TIMESTAMP, Instant.now());
        log.error(exception.getMessage(), exception);
        return detail;
    }

    public static ProblemDetail getMissingParameterProblemDetail (
        MissingServletRequestParameterException exception) {
        ProblemDetail detail = forStatusAndDetail(BAD_REQUEST, exception.getMessage());
        detail.setTitle("Bad Request, Missing Parameter");
        detail.setType(
            URI.create(SPRING_DOCS_URI + "/web/bind/MissingServletRequestParameterException.html"));
        detail.setProperty(CATEGORY, MISSING_PARAMETER);
        detail.setProperty(TIMESTAMP, Instant.now());
        log.error(exception.getMessage(), exception);
        return detail;
    }

    public static ProblemDetail getMethodArgumentNotValidProblemDetail (
        MethodArgumentNotValidException exception) {
        ProblemDetail detail = forStatusAndDetail(BAD_REQUEST, exception.getMessage());
        detail.setTitle("Bad Request, Validation failed for one or more arguments");
        detail.setType(URI.create(SPRING_DOCS_URI +
            "/messaging/handler/annotation/support/MethodArgumentNotValidException.html"));
        detail.setProperty(CATEGORY, MISSING_PARAMETER);
        detail.setProperty(TIMESTAMP, Instant.now());
        log.error(exception.getMessage(), exception);
        return detail;
    }

    public static ProblemDetail getMissingRequestBodyProblemDetail (
        HttpMessageNotReadableException exception) {
        ProblemDetail detail = forStatusAndDetail(BAD_REQUEST, exception.getMessage());
        detail.setTitle("Bad Request, Required request body is missing or unreadable");
        detail.setType(
            URI.create(SPRING_DOCS_URI + "/http/converter/HttpMessageNotReadableException.html"));
        detail.setProperty(CATEGORY, MISSING_PARAMETER);
        detail.setProperty(TIMESTAMP, Instant.now());
        log.error(exception.getMessage(), exception);
        return detail;
    }

    public static ProblemDetail getMissingPathVariableProblemDetail (
        MissingPathVariableException exception) {
        ProblemDetail detail = forStatusAndDetail(BAD_REQUEST, exception.getMessage());
        detail.setTitle("Bad request, Missing required path variables");
        detail.setType(URI.create(SPRING_DOCS_URI + "/web/bind/MissingPathVariableException.html"));
        detail.setProperty(CATEGORY, MISSING_PARAMETER);
        detail.setProperty(TIMESTAMP, Instant.now());
        log.error(exception.getMessage(), exception);
        return detail;
    }

    public static ProblemDetail getGeneralExceptionProblemDetail (Exception exception) {
        ProblemDetail detail = forStatusAndDetail(INTERNAL_SERVER_ERROR, exception.getMessage());
        detail.setTitle("General Error");
        detail.setType(URI.create(
            "https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/Exception" +
                ".html"));
        detail.setProperty(CATEGORY, INTERNAL_ERROR);
        detail.setProperty(TIMESTAMP, Instant.now());
        log.error(exception.getMessage(), exception);
        return detail;
    }
}
