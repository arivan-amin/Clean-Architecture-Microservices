package io.github.arivanamin.lms.backend.student.application.advice;

import io.github.arivanamin.lms.backend.student.core.exception.StudentAlreadyExistsException;
import io.github.arivanamin.lms.backend.student.core.exception.StudentNotFoundException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;

import static io.github.arivanamin.lms.backend.base.application.advice.ProblemDetailCategories.RESOURCE_NOT_FOUND;
import static io.github.arivanamin.lms.backend.base.application.advice.ProblemDetailExceptionUrls.RUNTIME_EXCEPTION_URL;
import static io.github.arivanamin.lms.backend.base.application.advice.ProblemDetailProperties.CATEGORY;
import static io.github.arivanamin.lms.backend.base.application.advice.ProblemDetailProperties.TIMESTAMP;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ProblemDetail.forStatusAndDetail;

@RestControllerAdvice
@NoArgsConstructor (access = AccessLevel.PRIVATE)
@Slf4j
public final class StudentControllerAdvice {

    @ExceptionHandler (StudentNotFoundException.class)
    ProblemDetail handleStudentNotFound (StudentNotFoundException exception) {
        ProblemDetail detail = forStatusAndDetail(NOT_FOUND, exception.getMessage());
        detail.setTitle("Requested Student Not Found");
        detail.setType(URI.create(RUNTIME_EXCEPTION_URL));
        detail.setProperty(CATEGORY, RESOURCE_NOT_FOUND);
        detail.setProperty(TIMESTAMP, Instant.now());
        log.error(exception.getMessage(), exception);
        return detail;
    }

    @ExceptionHandler (StudentAlreadyExistsException.class)
    ProblemDetail handleStudentNotFound (StudentAlreadyExistsException exception) {
        ProblemDetail detail = forStatusAndDetail(CONFLICT, exception.getMessage());
        detail.setTitle("Conflict, Student already exists");
        detail.setType(URI.create(RUNTIME_EXCEPTION_URL));
        detail.setProperty(CATEGORY, "Student Already Exists");
        detail.setProperty(TIMESTAMP, Instant.now());
        log.error(exception.getMessage(), exception);
        return detail;
    }
}
