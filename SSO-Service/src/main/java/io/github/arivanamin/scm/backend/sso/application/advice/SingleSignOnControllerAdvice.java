package io.github.arivanamin.scm.backend.sso.application.advice;

import io.github.arivanamin.scm.backend.sso.core.exception.ClientAlreadyExistsException;
import io.github.arivanamin.scm.backend.sso.core.exception.ClientNotFoundException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;

import static io.github.arivanamin.scm.backend.base.application.advice.ProblemDetailCategories.RESOURCE_NOT_FOUND;
import static io.github.arivanamin.scm.backend.base.application.advice.ProblemDetailProperties.CATEGORY;
import static io.github.arivanamin.scm.backend.base.application.advice.ProblemDetailProperties.TIMESTAMP;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ProblemDetail.forStatusAndDetail;

@RestControllerAdvice
@NoArgsConstructor (access = AccessLevel.PRIVATE)
@Slf4j
public final class SingleSignOnControllerAdvice {

    @ExceptionHandler (ClientNotFoundException.class)
    ProblemDetail handleClientNotFound (ClientNotFoundException exception) {
        ProblemDetail detail = forStatusAndDetail(NOT_FOUND, exception.getMessage());
        detail.setTitle("Requested Client Not Found");
        detail.setType(URI.create("https://docs.oracle.com/en/java/javase/21/docs/api/java" +
            ".base/java/lang/RuntimeException.html"));
        detail.setProperty(CATEGORY, RESOURCE_NOT_FOUND);
        detail.setProperty(TIMESTAMP, Instant.now());
        log.error(exception.getMessage(), exception);
        return detail;
    }

    @ExceptionHandler (ClientAlreadyExistsException.class)
    ProblemDetail handleClientNotFound (ClientAlreadyExistsException exception) {
        ProblemDetail detail = forStatusAndDetail(CONFLICT, exception.getMessage());
        detail.setTitle("Conflict, Client already exists");
        detail.setType(URI.create("https://docs.oracle.com/en/java/javase/21/docs/api/java" +
            ".base/java/lang/RuntimeException.html"));
        detail.setProperty(CATEGORY, "Client Already Exists");
        detail.setProperty(TIMESTAMP, Instant.now());
        log.error(exception.getMessage(), exception);
        return detail;
    }
}
