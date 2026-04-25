package io.github.arivanamin.cinemayan.core.application.advice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.net.URI;
import java.time.Clock;
import java.time.Instant;

import static io.github.arivanamin.cinemayan.core.application.advice.ProblemDetailCategories.*;
import static io.github.arivanamin.cinemayan.core.application.advice.ProblemDetailExceptionUrls.*;
import static io.github.arivanamin.cinemayan.core.application.advice.ProblemDetailProperties.*;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public final class CommonControllerAdvice {

    private final Clock clock;

    @ResponseStatus (BAD_REQUEST)
    @ExceptionHandler (MissingPathVariableException.class)
    ProblemDetail handleMissingPathVariable (MissingPathVariableException exception) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(BAD_REQUEST,
            "Bad request, Missing required path variables");
        detail.setTitle("Bad request, Missing required path variables");
        detail.setType(URI.create(MISSING_PATH_VARIABLE_EXCEPTION_URL));
        detail.setProperty(CATEGORY, MISSING_PARAMETER);
        detail.setProperty(TIMESTAMP, Instant.now(clock));
        detail.setProperty(TRACE_ID_KEY, TRACE_ID_VALUE);
        detail.setProperty(SPAN_ID_KEY, SPAN_ID_VALUE);
        log.warn("Bad request, Missing required path variables", exception);
        return detail;
    }

    @ResponseStatus (BAD_REQUEST)
    @ExceptionHandler (HttpMessageNotReadableException.class)
    ProblemDetail handleMissingRequestBody (HttpMessageNotReadableException exception) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(BAD_REQUEST,
            "Bad Request, Required request body is missing or unreadable");
        detail.setTitle("Bad Request, Required request body is missing or unreadable");
        detail.setType(URI.create(HTTP_MESSAGE_NOT_READABLE_EXCEPTION_URL));
        detail.setProperty(CATEGORY, MISSING_PARAMETER);
        detail.setProperty(TIMESTAMP, Instant.now(clock));
        detail.setProperty(TRACE_ID_KEY, TRACE_ID_VALUE);
        detail.setProperty(SPAN_ID_KEY, SPAN_ID_VALUE);
        log.warn("Bad Request, Required request body is missing or unreadable", exception);
        return detail;
    }

    @ResponseStatus (BAD_REQUEST)
    @ExceptionHandler (MethodArgumentNotValidException.class)
    ProblemDetail handleMethodArgumentNotValid (MethodArgumentNotValidException exception) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(BAD_REQUEST,
            "Bad Request, Validation failed for one or more arguments");
        detail.setTitle("Bad Request, Validation failed for one or more arguments");
        detail.setType(URI.create(METHOD_ARGUMENT_NOT_VALID_EXCEPTION_URL));
        detail.setProperty(CATEGORY, MISSING_PARAMETER);
        detail.setProperty(TIMESTAMP, Instant.now(clock));
        detail.setProperty(TRACE_ID_KEY, TRACE_ID_VALUE);
        detail.setProperty(SPAN_ID_KEY, SPAN_ID_VALUE);
        log.warn("Bad Request, Validation failed for one or more arguments", exception);
        return detail;
    }

    @ResponseStatus (BAD_REQUEST)
    @ExceptionHandler (MissingServletRequestParameterException.class)
    ProblemDetail handleMissingParameterException (
        MissingServletRequestParameterException exception) {
        ProblemDetail detail =
            ProblemDetail.forStatusAndDetail(BAD_REQUEST, "Bad Request, Missing Parameter");
        detail.setTitle("Bad Request, Missing Parameter");
        detail.setType(URI.create(MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION_URL));
        detail.setProperty(CATEGORY, MISSING_PARAMETER);
        detail.setProperty(TIMESTAMP, Instant.now(clock));
        detail.setProperty(TRACE_ID_KEY, TRACE_ID_VALUE);
        detail.setProperty(SPAN_ID_KEY, SPAN_ID_VALUE);
        log.warn("Bad Request, Missing Parameter", exception);
        return detail;
    }

    @ResponseStatus (NOT_FOUND)
    @ExceptionHandler (NoResourceFoundException.class)
    ProblemDetail handleResourceNotFound (NoResourceFoundException exception) {
        ProblemDetail detail =
            ProblemDetail.forStatusAndDetail(NOT_FOUND, "Requested Resource not found");
        detail.setTitle("Requested Resource not found");
        detail.setType(URI.create(SPRING_RESOURCE_NOT_FOUND_EXCEPTION_URL));
        detail.setProperty(CATEGORY, RESOURCE_NOT_FOUND);
        detail.setProperty(TIMESTAMP, Instant.now(clock));
        detail.setProperty(TRACE_ID_KEY, TRACE_ID_VALUE);
        detail.setProperty(SPAN_ID_KEY, SPAN_ID_VALUE);
        log.warn("Requested Resource not found", exception);
        return detail;
    }

    @ResponseStatus (INTERNAL_SERVER_ERROR)
    @ExceptionHandler (Exception.class)
    ProblemDetail handleGeneralExceptions (Exception exception) {
        ProblemDetail detail =
            ProblemDetail.forStatusAndDetail(INTERNAL_SERVER_ERROR, "Unhandled exception occurred");
        detail.setTitle("Unhandled exception occurred");
        detail.setType(URI.create(EXCEPTION_URL));
        detail.setProperty(CATEGORY, INTERNAL_ERROR);
        detail.setProperty(TIMESTAMP, Instant.now(clock));
        detail.setProperty(TRACE_ID_KEY, TRACE_ID_VALUE);
        detail.setProperty(SPAN_ID_KEY, SPAN_ID_VALUE);
        log.warn("Unhandled Exception occurred", exception);
        return detail;
    }
}
