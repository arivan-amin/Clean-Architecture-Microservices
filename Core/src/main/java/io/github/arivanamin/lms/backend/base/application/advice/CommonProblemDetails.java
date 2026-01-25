package io.github.arivanamin.lms.backend.base.application.advice;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.*;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.net.URI;
import java.time.Instant;

import static io.github.arivanamin.lms.backend.base.application.advice.ProblemDetailCategories.*;
import static io.github.arivanamin.lms.backend.base.application.advice.ProblemDetailExceptionUrls.*;
import static io.github.arivanamin.lms.backend.base.application.advice.ProblemDetailProperties.CATEGORY;
import static io.github.arivanamin.lms.backend.base.application.advice.ProblemDetailProperties.TIMESTAMP;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ProblemDetail.forStatusAndDetail;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
@Slf4j
public final class CommonProblemDetails {

    public static ProblemDetail getResourceNotFoundProblemDetail (
        NoResourceFoundException exception) {
        ProblemDetail detail = forStatusAndDetail(NOT_FOUND, exception.getMessage());
        detail.setTitle("Requested Resource not found");
        detail.setType(URI.create(SPRING_RESOURCE_NOT_FOUND_EXCEPTION_URL));
        detail.setProperty(CATEGORY, RESOURCE_NOT_FOUND);
        detail.setProperty(TIMESTAMP, Instant.now());
        log.error(exception.getMessage(), exception);
        return detail;
    }

    public static ProblemDetail getMissingParameterProblemDetail (
        MissingServletRequestParameterException exception) {
        ProblemDetail detail = forStatusAndDetail(BAD_REQUEST, exception.getMessage());
        detail.setTitle("Bad Request, Missing Parameter");
        detail.setType(URI.create(MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION_URL));
        detail.setProperty(CATEGORY, MISSING_PARAMETER);
        detail.setProperty(TIMESTAMP, Instant.now());
        log.error(exception.getMessage(), exception);
        return detail;
    }

    public static ProblemDetail getMethodArgumentNotValidProblemDetail (
        MethodArgumentNotValidException exception) {
        ProblemDetail detail = forStatusAndDetail(BAD_REQUEST, exception.getMessage());
        detail.setTitle("Bad Request, Validation failed for one or more arguments");
        detail.setType(URI.create(METHOD_ARGUMENT_NOT_VALID_EXCEPTION_URL));
        detail.setProperty(CATEGORY, MISSING_PARAMETER);
        detail.setProperty(TIMESTAMP, Instant.now());
        log.error(exception.getMessage(), exception);
        return detail;
    }

    public static ProblemDetail getMissingRequestBodyProblemDetail (
        HttpMessageNotReadableException exception) {
        ProblemDetail detail = forStatusAndDetail(BAD_REQUEST, exception.getMessage());
        detail.setTitle("Bad Request, Required request body is missing or unreadable");
        detail.setType(URI.create(HTTP_MESSAGE_NOT_READABLE_EXCEPTION_URL));
        detail.setProperty(CATEGORY, MISSING_PARAMETER);
        detail.setProperty(TIMESTAMP, Instant.now());
        log.error(exception.getMessage(), exception);
        return detail;
    }

    public static ProblemDetail getMissingPathVariableProblemDetail (
        MissingPathVariableException exception) {
        ProblemDetail detail = forStatusAndDetail(BAD_REQUEST, exception.getMessage());
        detail.setTitle("Bad request, Missing required path variables");
        detail.setType(URI.create(MISSING_PATH_VARIABLE_EXCEPTION_URL));
        detail.setProperty(CATEGORY, MISSING_PARAMETER);
        detail.setProperty(TIMESTAMP, Instant.now());
        log.error(exception.getMessage(), exception);
        return detail;
    }

    public static ProblemDetail getGeneralExceptionProblemDetail (Exception exception) {
        ProblemDetail detail = forStatusAndDetail(INTERNAL_SERVER_ERROR, exception.getMessage());
        detail.setTitle("General Error");
        detail.setType(URI.create(EXCEPTION_URL));
        detail.setProperty(CATEGORY, INTERNAL_ERROR);
        detail.setProperty(TIMESTAMP, Instant.now());
        log.error(exception.getMessage(), exception);
        return detail;
    }
}
