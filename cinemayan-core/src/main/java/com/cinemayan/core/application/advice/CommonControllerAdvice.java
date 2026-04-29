package com.cinemayan.core.application.advice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;

import static com.cinemayan.core.application.advice.ProblemDetailCategories.*;
import static com.cinemayan.core.application.advice.ProblemDetailExceptionUrls.*;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public final class CommonControllerAdvice {

    private final ProblemDetailFactory factory;

    @ExceptionHandler (AsyncRequestTimeoutException.class)
    ProblemDetail handleAsyncRequestTimeoutException (AsyncRequestTimeoutException exception,
                                                      HttpServletRequest request) {
        log.error("Async request timed out, server failed to process request in time: method={}, " +
                "uri={}, client={}", request.getMethod(), request.getRequestURI(),
            request.getRemoteAddr(), exception);

        String title = "Async Request Timed Out";
        String detail = "Server failed to process request in time";
        return factory.build(SERVICE_UNAVAILABLE, title, detail, INTERNAL_ERROR,
            ASYNC_REQUEST_TIMEOUT_EXCEPTION_URL);
    }

    @ExceptionHandler (OptimisticLockingFailureException.class)
    ProblemDetail handleOptimisticLockingFailureException (
        OptimisticLockingFailureException exception, HttpServletRequest request) {
        log.error(
            "Optimistic locking conflict, concurrent modification detected: method={}, uri={}, " +
                "client={}, reason={}", request.getMethod(), request.getRequestURI(),
            request.getRemoteAddr(), exception.getMessage(), exception);

        String title = "Optimistic Locking Conflict";
        String detail = "Concurrent modification detected: %s".formatted(exception.getMessage());
        return factory.build(CONFLICT, title, detail, CONCURRENCY_CONFLICT,
            OPTIMISTIC_LOCKING_FAILURE_EXCEPTION_URL);
    }

    @ExceptionHandler (MethodArgumentTypeMismatchException.class)
    ProblemDetail handleMethodArgumentTypeMismatchException (
        MethodArgumentTypeMismatchException exception, HttpServletRequest request) {
        log.warn("Method argument type mismatch: method={}, uri={}, client={}, param={}, " +
                "providedValue={}, expectedType={}", request.getMethod(), request.getRequestURI(),
            request.getRemoteAddr(), exception.getName(), exception.getValue(),
            exception.getRequiredType());

        String title = "Bad request, Method Argument Type Mismatch";
        String detail =
            "Type mismatch on parameter: %s, provided Value: %s, expected Type: %s".formatted(
                exception.getName(), exception.getValue(), exception.getRequiredType());
        return factory.build(BAD_REQUEST, title, detail, BAD_OR_UNREADABLE_REQUEST,
            METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION_URL);
    }

    @ExceptionHandler (HttpMediaTypeNotAcceptableException.class)
    ProblemDetail handleHttpMediaTypeNotAcceptableException (
        HttpMediaTypeNotAcceptableException exception, HttpServletRequest request) {
        log.warn("Media type not acceptable: method={}, uri={}, client={}, acceptHeader={}, " +
                "supportedMediaTypes={}", request.getMethod(), request.getRequestURI(),
            request.getRemoteAddr(), request.getHeader("Accept"),
            exception.getSupportedMediaTypes());

        String title = "Bad request, Media Type Not Acceptable";
        String detail =
            "Acceptable media types are: %s".formatted(exception.getSupportedMediaTypes());
        return factory.build(NOT_ACCEPTABLE, title, detail, BAD_OR_UNREADABLE_REQUEST,
            HTTP_MEDIA_TYPE_NOT_ACCEPTABLE_EXCEPTION_URL);
    }

    @ExceptionHandler (HttpMediaTypeNotSupportedException.class)
    ProblemDetail handleHttpMediaTypeNotSupportedException (
        HttpMediaTypeNotSupportedException exception, HttpServletRequest request) {
        log.warn("Unsupported media type: method={}, uri={}, client={}, contentType={}, " +
                "supportedMediaTypes={}", request.getMethod(), request.getRequestURI(),
            request.getRemoteAddr(), exception.getContentType(),
            exception.getSupportedMediaTypes());

        String title = "Bad request, Media Type Not Supported";
        String detail =
            "Supported Media types are: %s".formatted(exception.getSupportedMediaTypes());
        return factory.build(UNSUPPORTED_MEDIA_TYPE, title, detail, BAD_OR_UNREADABLE_REQUEST,
            HTTP_MEDIA_TYPE_NOT_SUPPORTED_EXCEPTION_URL);
    }

    @ExceptionHandler (HttpRequestMethodNotSupportedException.class)
    ProblemDetail handleHttpRequestMethodNotSupportedException (
        HttpRequestMethodNotSupportedException exception, HttpServletRequest request) {
        log.warn("HTTP method not allowed: method={}, uri={}, client={}, supportedMethods={}",
            request.getMethod(), request.getRequestURI(), request.getRemoteAddr(),
            exception.getSupportedHttpMethods());

        String title = "Bad Request, Method Not Allowed";
        String detail = "Request doesn't allow method: %s".formatted(request.getMethod());
        return factory.build(METHOD_NOT_ALLOWED, title, detail, BAD_OR_UNREADABLE_REQUEST,
            HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION_URL);
    }

    @ExceptionHandler (ConstraintViolationException.class)
    ProblemDetail handleConstraintViolation (ConstraintViolationException exception,
                                             HttpServletRequest request) {
        List<String> violations = exception.getConstraintViolations()
            .stream()
            .map(violation -> violation.getPropertyPath() + ":" + violation.getMessage())
            .toList();

        log.warn("Constraint violation: method={}, uri={}, client={}, violations={}",
            request.getMethod(), request.getRequestURI(), request.getRemoteAddr(), violations);

        String title = "Bad request, Constraint Violation";
        String detail = "Request violates following constraints: %s".formatted(violations);
        return factory.build(BAD_REQUEST, title, detail, VALIDATION_ERROR,
            CONSTRAINT_VIOLATION_EXCEPTION_URL);
    }

    @ExceptionHandler (MissingPathVariableException.class)
    ProblemDetail handleMissingPathVariable (MissingPathVariableException exception,
                                             HttpServletRequest request) {
        log.warn("Bad request, Missing required path variables [uri={}, variable={}]",
            request.getRequestURI(), exception.getVariableName(), exception);

        String title = "Bad request, Missing Path Variable";
        String detail = "Missing required path variable: %s".formatted(exception.getVariableName());
        return factory.build(BAD_REQUEST, title, detail, MISSING_PARAMETER,
            MISSING_PATH_VARIABLE_EXCEPTION_URL);
    }

    @ExceptionHandler (HttpMessageNotReadableException.class)
    ProblemDetail handleMissingRequestBody (HttpMessageNotReadableException exception,
                                            HttpServletRequest request) {
        log.warn(
            "Bad request, Malformed or missing request body [method = {},uri = {},contentType = " +
                "{},client = {},reason = {}", request.getMethod(), request.getRequestURI(),
            request.getContentType(), request.getRemoteAddr(), exception.getMessage());

        String title = "Bad Request, Missing Required Request Body";
        String detail = "Required request body is missing or unreadable for: %s".formatted(
            exception.getMessage());
        return factory.build(BAD_REQUEST, title, detail, MISSING_PARAMETER,
            HTTP_MESSAGE_NOT_READABLE_EXCEPTION_URL);
    }

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

        String title = "Bad Request, Validation failed";
        String detail = "Validation failed for %s".formatted(violations);
        return factory.build(BAD_REQUEST, title, detail, MISSING_PARAMETER,
            METHOD_ARGUMENT_NOT_VALID_EXCEPTION_URL);
    }

    @ExceptionHandler (MissingServletRequestParameterException.class)
    ProblemDetail handleMissingParameterException (
        MissingServletRequestParameterException exception, HttpServletRequest request) {
        log.warn(
            "Missing required request parameter: [method = {}, uri = {}, client = {}, paramName =" +
                " {}, " + "paramType = {}]", request.getMethod(), request.getRequestURI(),
            request.getRemoteAddr(), exception.getParameterName(), exception.getParameterType());

        String title = "Bad Request, Missing Parameter";
        String detail =
            "Request is missing parameter %s of type: %s".formatted(exception.getParameterName(),
                exception.getParameterType());
        return factory.build(BAD_REQUEST, title, detail, MISSING_PARAMETER,
            MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION_URL);
    }

    @ExceptionHandler (NoResourceFoundException.class)
    ProblemDetail handleResourceNotFound (NoResourceFoundException exception,
                                          HttpServletRequest request) {
        log.warn("Resource not found: method={}, uri={}, client={}, resourcePath={}",
            request.getMethod(), request.getRequestURI(), request.getRemoteAddr(),
            exception.getResourcePath());

        String title = "Resource Not Found";
        String detail = "Requested Resource: %s, not found".formatted(exception.getResourcePath());
        return factory.build(NOT_FOUND, title, detail, RESOURCE_NOT_FOUND,
            RESOURCE_NOT_FOUND_EXCEPTION_URL);
    }

    @ExceptionHandler (Exception.class)
    ProblemDetail handleGeneralExceptions (Exception exception) {
        log.error("Unhandled Exception occurred", exception);

        String title = "Unhandled Exception";
        String detail = "Unhandled internal exception occurred";
        return factory.build(INTERNAL_SERVER_ERROR, title, detail, INTERNAL_ERROR, EXCEPTION_URL);
    }
}
