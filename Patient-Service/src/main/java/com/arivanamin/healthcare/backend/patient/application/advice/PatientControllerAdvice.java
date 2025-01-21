package com.arivanamin.healthcare.backend.patient.application.advice;

import com.arivanamin.healthcare.backend.patient.core.exception.PatientAlreadyExistsException;
import com.arivanamin.healthcare.backend.patient.core.exception.PatientNotFoundException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.net.URI;
import java.time.Instant;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ProblemDetail.forStatusAndDetail;

@Slf4j
@RestControllerAdvice
@NoArgsConstructor (access = AccessLevel.PRIVATE)
public final class PatientControllerAdvice {
    
    @ExceptionHandler (PatientNotFoundException.class)
    ProblemDetail handlePatientNotFound (PatientNotFoundException exception) {
        ProblemDetail detail = forStatusAndDetail(NOT_FOUND, exception.getMessage());
        detail.setTitle("Bad Request, Patient not found");
        detail.setType(URI.create("https://docs.oracle.com/en/java/javase/21/docs/api/java" +
            ".base/java/lang/RuntimeException.html"));
        detail.setProperty("errorCategory", "Resource not found");
        detail.setProperty("timestamp", Instant.now());
        detail.setDetail(exception.getMessage());
        log.error("PatientNotFoundException advice", exception);
        return detail;
    }
    
    @ExceptionHandler (PatientAlreadyExistsException.class)
    ProblemDetail handlePatientNotFound (PatientAlreadyExistsException exception) {
        ProblemDetail detail = forStatusAndDetail(CONFLICT, exception.getMessage());
        detail.setTitle("Conflict, Patient already exists");
        detail.setType(URI.create("https://docs.oracle.com/en/java/javase/21/docs/api/java" +
            ".base/java/lang/RuntimeException.html"));
        detail.setProperty("errorCategory", "Patient already exists");
        detail.setProperty("timestamp", Instant.now());
        detail.setDetail(exception.getMessage());
        log.error("PatientAlreadyExistsException advice", exception);
        return detail;
    }
    
    @ExceptionHandler (MissingPathVariableException.class)
    ProblemDetail handleMissingPathVariable (MissingPathVariableException exception) {
        ProblemDetail detail = forStatusAndDetail(BAD_REQUEST, exception.getMessage());
        detail.setTitle("Bad request, Missing required path variables");
        detail.setType(URI.create(
            "https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework" +
                "/web/bind/MissingPathVariableException.html"));
        detail.setProperty("errorCategory", "Missing Parameter");
        detail.setProperty("timestamp", Instant.now());
        detail.setDetail(exception.getMessage());
        log.error("MissingPathVariableException advice", exception);
        return detail;
    }
    
    @ExceptionHandler (HttpMessageNotReadableException.class)
    ProblemDetail handleMissingRequestBody (HttpMessageNotReadableException exception) {
        ProblemDetail detail = forStatusAndDetail(BAD_REQUEST, exception.getMessage());
        detail.setTitle("Bad Request, Required request body is missing or unreadable");
        detail.setType(URI.create(
            "https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework" +
                "/http/converter/HttpMessageNotReadableException.html"));
        detail.setProperty("errorCategory", "Missing Parameter");
        detail.setProperty("timestamp", Instant.now());
        detail.setDetail(exception.getMessage());
        log.error("HttpMessageNotReadableException advice", exception);
        return detail;
    }
    
    @ExceptionHandler (MethodArgumentNotValidException.class)
    ProblemDetail handleMethodArgumentNotValid (MethodArgumentNotValidException exception) {
        ProblemDetail detail = forStatusAndDetail(BAD_REQUEST, exception.getMessage());
        detail.setTitle("Bad Request, Validation failed for one or more arguments");
        detail.setType(URI.create(
            "https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework" +
                "/messaging/handler/annotation/support/MethodArgumentNotValidException.html"));
        detail.setProperty("errorCategory", "Missing Parameter");
        detail.setProperty("timestamp", Instant.now());
        detail.setDetail(exception.getMessage());
        log.error("MethodArgumentNotValidException advice", exception);
        return detail;
    }
    
    @ExceptionHandler (MissingServletRequestParameterException.class)
    ProblemDetail handleMissingParameterException (
        MissingServletRequestParameterException exception) {
        ProblemDetail detail = forStatusAndDetail(BAD_REQUEST, exception.getMessage());
        detail.setTitle("Bad Request, Missing Parameter");
        detail.setType(URI.create(
            "https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework" +
                "/web/bind/MissingServletRequestParameterException.html"));
        detail.setProperty("errorCategory", "Missing Parameter");
        detail.setProperty("timestamp", Instant.now());
        detail.setDetail(exception.getMessage());
        log.error("MissingServletRequestParameterException advice", exception);
        return detail;
    }
    
    @ExceptionHandler (NoResourceFoundException.class)
    ProblemDetail handleResourceNotFound (NoResourceFoundException exception) {
        ProblemDetail detail = forStatusAndDetail(NOT_FOUND, exception.getMessage());
        detail.setTitle("Resource not found");
        detail.setType(URI.create(
            "https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework" +
                "/web/servlet/resource/NoResourceFoundException.html" +
                ".base/java/lang/RuntimeException.html"));
        detail.setProperty("errorCategory", "Resource not found");
        detail.setProperty("timestamp", Instant.now());
        detail.setDetail(exception.getMessage());
        log.error("NoResourceFoundException advice", exception);
        return detail;
    }
    
    @ExceptionHandler (Exception.class)
    ProblemDetail handleGeneralExceptions (Exception exception) {
        ProblemDetail detail = forStatusAndDetail(INTERNAL_SERVER_ERROR, exception.getMessage());
        detail.setTitle("General Error: ");
        detail.setType(URI.create(
            "https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/Exception" +
                ".html"));
        detail.setProperty("errorCategory", "Internal Error");
        detail.setProperty("timestamp", Instant.now());
        detail.setDetail(exception.getMessage());
        log.error("Exception advice", exception);
        return detail;
    }
}
