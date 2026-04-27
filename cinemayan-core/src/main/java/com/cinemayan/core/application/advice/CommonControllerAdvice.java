package com.cinemayan.core.application.advice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.net.URI;
import java.time.Clock;
import java.time.Instant;
import java.util.List;

import static com.cinemayan.core.application.advice.ProblemDetailCategories.*;
import static com.cinemayan.core.application.advice.ProblemDetailExceptionUrls.*;
import static com.cinemayan.core.application.advice.ProblemDetailProperties.*;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public final class CommonControllerAdvice {

    private final Clock clock;

    @ResponseStatus (SERVICE_UNAVAILABLE)
    @ExceptionHandler (AsyncRequestTimeoutException.class)
    ProblemDetail handleAsyncRequestTimeoutException (AsyncRequestTimeoutException exception,
                                                      HttpServletRequest request) {
        log.error("Async request timed out, server failed to process request in time: method={}, " +
                "uri={}, client={}", request.getMethod(), request.getRequestURI(),
            request.getRemoteAddr());

        ProblemDetail detail = ProblemDetail.forStatusAndDetail(BAD_REQUEST,
            "Bad request, Missing required path variables");
        detail.setTitle("Bad request, Missing required path variables");
        detail.setType(URI.create(ASYNC_REQUEST_TIMEOUT_EXCEPTION_URL));
        detail.setProperty(CATEGORY, MISSING_PARAMETER);
        detail.setProperty(TIMESTAMP, Instant.now(clock));
        detail.setProperty(TRACE_ID_KEY, MDC.get(TRACE_ID_VALUE));
        detail.setProperty(SPAN_ID_KEY, MDC.get(SPAN_ID_VALUE));
        return detail;
    }

    @ResponseStatus (CONFLICT)
    @ExceptionHandler (OptimisticLockingFailureException.class)
    ProblemDetail handleOptimisticLockingFailureException (
        OptimisticLockingFailureException exception, HttpServletRequest request) {
        log.warn(
            "Optimistic locking conflict, concurrent modification detected: method={}, uri={}, " +
                "client={}, reason={}", request.getMethod(), request.getRequestURI(),
            request.getRemoteAddr(), exception.getMessage());

        ProblemDetail detail = ProblemDetail.forStatusAndDetail(BAD_REQUEST,
            "Bad request, Missing required path variables");
        detail.setTitle("Bad request, Missing required path variables");
        detail.setType(URI.create(OPTIMISTIC_LOCKING_FAILURE_EXCEPTION_URL));
        detail.setProperty(CATEGORY, MISSING_PARAMETER);
        detail.setProperty(TIMESTAMP, Instant.now(clock));
        detail.setProperty(TRACE_ID_KEY, MDC.get(TRACE_ID_VALUE));
        detail.setProperty(SPAN_ID_KEY, MDC.get(SPAN_ID_VALUE));
        return detail;
    }

    @ResponseStatus (BAD_REQUEST)
    @ExceptionHandler (MethodArgumentTypeMismatchException.class)
    ProblemDetail handleMethodArgumentTypeMismatchException (
        MethodArgumentTypeMismatchException exception, HttpServletRequest request) {
        log.warn("Method argument type mismatch: method={}, uri={}, client={}, param={}, " +
                "providedValue={}, expectedType={}", request.getMethod(), request.getRequestURI(),
            request.getRemoteAddr(), exception.getName(), exception.getValue(),
            exception.getRequiredType());

        ProblemDetail detail = ProblemDetail.forStatusAndDetail(BAD_REQUEST,
            "Bad request, Missing required path variables");
        detail.setTitle("Bad request, Missing required path variables");
        detail.setType(URI.create(METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION_URL));
        detail.setProperty(CATEGORY, MISSING_PARAMETER);
        detail.setProperty(TIMESTAMP, Instant.now(clock));
        detail.setProperty(TRACE_ID_KEY, MDC.get(TRACE_ID_VALUE));
        detail.setProperty(SPAN_ID_KEY, MDC.get(SPAN_ID_VALUE));
        return detail;
    }

    @ResponseStatus (NOT_ACCEPTABLE)
    @ExceptionHandler (HttpMediaTypeNotAcceptableException.class)
    ProblemDetail handleHttpMediaTypeNotAcceptableException (
        HttpMediaTypeNotAcceptableException exception, HttpServletRequest request) {
        log.warn("Media type not acceptable: method={}, uri={}, client={}, acceptHeader={}, " +
                "supportedMediaTypes={}", request.getMethod(), request.getRequestURI(),
            request.getRemoteAddr(), request.getHeader("Accept"),
            exception.getSupportedMediaTypes());

        ProblemDetail detail = ProblemDetail.forStatusAndDetail(BAD_REQUEST,
            "Bad request, Missing required path variables");
        detail.setTitle("Bad request, Missing required path variables");
        detail.setType(URI.create(HTTP_MEDIA_TYPE_NOT_ACCEPTABLE_EXCEPTION_URL));
        detail.setProperty(CATEGORY, MISSING_PARAMETER);
        detail.setProperty(TIMESTAMP, Instant.now(clock));
        detail.setProperty(TRACE_ID_KEY, MDC.get(TRACE_ID_VALUE));
        detail.setProperty(SPAN_ID_KEY, MDC.get(SPAN_ID_VALUE));
        return detail;
    }

    @ResponseStatus (UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler (HttpMediaTypeNotSupportedException.class)
    ProblemDetail handleHttpMediaTypeNotSupportedException (
        HttpMediaTypeNotSupportedException exception, HttpServletRequest request) {
        log.warn("Unsupported media type: method={}, uri={}, client={}, contentType={}, " +
                "supportedMediaTypes={}", request.getMethod(), request.getRequestURI(),
            request.getRemoteAddr(), exception.getContentType(),
            exception.getSupportedMediaTypes());

        ProblemDetail detail = ProblemDetail.forStatusAndDetail(BAD_REQUEST,
            "Bad request, Missing required path variables");
        detail.setTitle("Bad request, Missing required path variables");
        detail.setType(URI.create(HTTP_MEDIA_TYPE_NOT_SUPPORTED_EXCEPTION_URL));
        detail.setProperty(CATEGORY, MISSING_PARAMETER);
        detail.setProperty(TIMESTAMP, Instant.now(clock));
        detail.setProperty(TRACE_ID_KEY, MDC.get(TRACE_ID_VALUE));
        detail.setProperty(SPAN_ID_KEY, MDC.get(SPAN_ID_VALUE));
        return detail;
    }

    @ResponseStatus (METHOD_NOT_ALLOWED)
    @ExceptionHandler (HttpRequestMethodNotSupportedException.class)
    ProblemDetail handleHttpRequestMethodNotSupportedException (
        HttpRequestMethodNotSupportedException exception, HttpServletRequest request) {
        log.warn("HTTP method not allowed: method={}, uri={}, client={}, supportedMethods={}",
            request.getMethod(), request.getRequestURI(), request.getRemoteAddr(),
            exception.getSupportedHttpMethods());

        ProblemDetail detail = ProblemDetail.forStatusAndDetail(BAD_REQUEST,
            "Bad request, Missing required path variables");
        detail.setTitle("Bad request, Missing required path variables");
        detail.setType(URI.create(HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION_URL));
        detail.setProperty(CATEGORY, MISSING_PARAMETER);
        detail.setProperty(TIMESTAMP, Instant.now(clock));
        detail.setProperty(TRACE_ID_KEY, MDC.get(TRACE_ID_VALUE));
        detail.setProperty(SPAN_ID_KEY, MDC.get(SPAN_ID_VALUE));
        return detail;
    }

    @ResponseStatus (BAD_REQUEST)
    @ExceptionHandler (ConstraintViolationException.class)
    ProblemDetail handleConstraintViolation (ConstraintViolationException exception,
                                             HttpServletRequest request) {
        List<String> violations = exception.getConstraintViolations()
            .stream()
            .map(violation -> violation.getPropertyPath() + ":" + violation.getMessage())
            .toList();

        log.warn("Constraint violation: method={}, uri={}, client={}, violations={}",
            request.getMethod(), request.getRequestURI(), request.getRemoteAddr(), violations);

        ProblemDetail detail = ProblemDetail.forStatusAndDetail(BAD_REQUEST,
            "Bad request, Missing required path variables");
        detail.setTitle("Bad request, Missing required path variables");
        detail.setType(URI.create(MISSING_PATH_VARIABLE_EXCEPTION_URL));
        detail.setProperty(CATEGORY, MISSING_PARAMETER);
        detail.setProperty(TIMESTAMP, Instant.now(clock));
        detail.setProperty(TRACE_ID_KEY, MDC.get(TRACE_ID_VALUE));
        detail.setProperty(SPAN_ID_KEY, MDC.get(SPAN_ID_VALUE));
        return detail;
    }

    @ResponseStatus (BAD_REQUEST)
    @ExceptionHandler (MissingPathVariableException.class)
    ProblemDetail handleMissingPathVariable (MissingPathVariableException exception,
                                             HttpServletRequest request) {
        log.warn("Bad request, Missing required path variables [uri={}, variable={}]",
            request.getRequestURI(), exception.getVariableName(), exception);

        ProblemDetail detail = ProblemDetail.forStatusAndDetail(BAD_REQUEST,
            "Bad request, Missing required path variables");
        detail.setTitle("Bad request, Missing required path variables");
        detail.setType(URI.create(MISSING_PATH_VARIABLE_EXCEPTION_URL));
        detail.setProperty(CATEGORY, MISSING_PARAMETER);
        detail.setProperty(TIMESTAMP, Instant.now(clock));
        detail.setProperty(TRACE_ID_KEY, MDC.get(TRACE_ID_VALUE));
        detail.setProperty(SPAN_ID_KEY, MDC.get(SPAN_ID_VALUE));
        return detail;
    }

    @ResponseStatus (BAD_REQUEST)
    @ExceptionHandler (HttpMessageNotReadableException.class)
    ProblemDetail handleMissingRequestBody (HttpMessageNotReadableException exception,
                                            HttpServletRequest request) {
        log.warn(
            "Bad request, Malformed or missing request body [method = {},uri = {},contentType = " +
                "{},client = {},reason = {}", request.getMethod(), request.getRequestURI(),
            request.getContentType(), request.getRemoteAddr(), exception.getMessage());

        ProblemDetail detail = ProblemDetail.forStatusAndDetail(BAD_REQUEST,
            "Bad Request, Required request body is missing or unreadable");
        detail.setTitle("Bad Request, Required request body is missing or unreadable");
        detail.setType(URI.create(HTTP_MESSAGE_NOT_READABLE_EXCEPTION_URL));
        detail.setProperty(CATEGORY, MISSING_PARAMETER);
        detail.setProperty(TIMESTAMP, Instant.now(clock));
        detail.setProperty(TRACE_ID_KEY, MDC.get(TRACE_ID_VALUE));
        detail.setProperty(SPAN_ID_KEY, MDC.get(SPAN_ID_VALUE));
        return detail;
    }

    @ResponseStatus (BAD_REQUEST)
    @ExceptionHandler (MethodArgumentNotValidException.class)
    ProblemDetail handleMethodArgumentNotValid (MethodArgumentNotValidException exception,
                                                HttpServletRequest request) {
        List<String> violations = exception.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> "%s:%s".formatted(error.getField(), error.getDefaultMessage()))
            .toList();
        log.warn(
            "Bad Request, Validation failed [method = {},uri = {}, client = {}, validations = {}]",
            request.getMethod(), request.getRequestURI(), request.getRemoteAddr(), violations);

        ProblemDetail detail = ProblemDetail.forStatusAndDetail(BAD_REQUEST,
            "Bad Request, Validation failed for one or more arguments");
        detail.setTitle("Bad Request, Validation failed for one or more arguments");
        detail.setType(URI.create(METHOD_ARGUMENT_NOT_VALID_EXCEPTION_URL));
        detail.setProperty(CATEGORY, MISSING_PARAMETER);
        detail.setProperty(TIMESTAMP, Instant.now(clock));
        detail.setProperty(TRACE_ID_KEY, MDC.get(TRACE_ID_VALUE));
        detail.setProperty(SPAN_ID_KEY, MDC.get(SPAN_ID_VALUE));
        return detail;
    }

    @ResponseStatus (BAD_REQUEST)
    @ExceptionHandler (MissingServletRequestParameterException.class)
    ProblemDetail handleMissingParameterException (
        MissingServletRequestParameterException exception, HttpServletRequest request) {
        log.warn(
            "Missing required request parameter: [method = {}, uri = {}, client = {}, paramName =" +
                " {}, " + "paramType = {}]", request.getMethod(), request.getRequestURI(),
            request.getRemoteAddr(), exception.getParameterName(), exception.getParameterType());

        ProblemDetail detail =
            ProblemDetail.forStatusAndDetail(BAD_REQUEST, "Bad Request, Missing Parameter");
        detail.setTitle("Bad Request, Missing Parameter");
        detail.setType(URI.create(MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION_URL));
        detail.setProperty(CATEGORY, MISSING_PARAMETER);
        detail.setProperty(TIMESTAMP, Instant.now(clock));
        detail.setProperty(TRACE_ID_KEY, MDC.get(TRACE_ID_VALUE));
        detail.setProperty(SPAN_ID_KEY, MDC.get(SPAN_ID_VALUE));
        return detail;
    }

    @ResponseStatus (NOT_FOUND)
    @ExceptionHandler (NoResourceFoundException.class)
    ProblemDetail handleResourceNotFound (NoResourceFoundException exception,
                                          HttpServletRequest request) {
        log.warn("Resource not found: method={}, uri={}, client={}, resourcePath={}",
            request.getMethod(), request.getRequestURI(), request.getRemoteAddr(),
            exception.getResourcePath());

        ProblemDetail detail =
            ProblemDetail.forStatusAndDetail(NOT_FOUND, "Requested Resource not found");
        detail.setTitle("Requested Resource not found");
        detail.setType(URI.create(SPRING_RESOURCE_NOT_FOUND_EXCEPTION_URL));
        detail.setProperty(CATEGORY, RESOURCE_NOT_FOUND);
        detail.setProperty(TIMESTAMP, Instant.now(clock));
        detail.setProperty(TRACE_ID_KEY, MDC.get(TRACE_ID_VALUE));
        detail.setProperty(SPAN_ID_KEY, MDC.get(SPAN_ID_VALUE));
        return detail;
    }

    @ResponseStatus (INTERNAL_SERVER_ERROR)
    @ExceptionHandler (Exception.class)
    ProblemDetail handleGeneralExceptions (Exception exception) {
        log.error("Unhandled Exception occurred", exception);

        ProblemDetail detail =
            ProblemDetail.forStatusAndDetail(INTERNAL_SERVER_ERROR, "Unhandled exception occurred");
        detail.setTitle("Unhandled exception occurred");
        detail.setType(URI.create(EXCEPTION_URL));
        detail.setProperty(CATEGORY, INTERNAL_ERROR);
        detail.setProperty(TIMESTAMP, Instant.now(clock));
        detail.setProperty(TRACE_ID_KEY, MDC.get(TRACE_ID_VALUE));
        detail.setProperty(SPAN_ID_KEY, MDC.get(SPAN_ID_VALUE));
        return detail;
    }
}
