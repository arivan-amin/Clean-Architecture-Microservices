package com.arivanamin.healthcare.backend.patient.application.advice;

import com.arivanamin.healthcare.backend.patient.core.exception.PatientAlreadyExistsException;
import com.arivanamin.healthcare.backend.patient.core.exception.PatientNotFoundException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ProblemDetail.forStatusAndDetail;

@RestControllerAdvice
@NoArgsConstructor (access = AccessLevel.PRIVATE)
@Slf4j
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
}
