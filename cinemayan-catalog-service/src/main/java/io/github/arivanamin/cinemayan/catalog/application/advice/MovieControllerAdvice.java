package io.github.arivanamin.cinemayan.catalog.application.advice;

import io.github.arivanamin.cinemayan.catalog.domain.movie.exception.MovieAlreadyExistsException;
import io.github.arivanamin.cinemayan.catalog.domain.movie.exception.MovieNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;

import static io.github.arivanamin.cinemayan.core.application.advice.ProblemDetailCategories.RESOURCE_NOT_FOUND;
import static io.github.arivanamin.cinemayan.core.application.advice.ProblemDetailExceptionUrls.RUNTIME_EXCEPTION_URL;
import static io.github.arivanamin.cinemayan.core.application.advice.ProblemDetailProperties.CATEGORY;
import static io.github.arivanamin.cinemayan.core.application.advice.ProblemDetailProperties.TIMESTAMP;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ProblemDetail.forStatusAndDetail;

@RestControllerAdvice
@Slf4j
public final class MovieControllerAdvice {

    @ExceptionHandler (MovieNotFoundException.class)
    ProblemDetail handleMovieNotFound (MovieNotFoundException exception) {
        ProblemDetail detail = forStatusAndDetail(NOT_FOUND, exception.getMessage());
        detail.setTitle("Requested Movie Not Found");
        detail.setType(URI.create(RUNTIME_EXCEPTION_URL));
        detail.setProperty(CATEGORY, RESOURCE_NOT_FOUND);
        detail.setProperty(TIMESTAMP, Instant.now());
        log.error(exception.getMessage(), exception);
        return detail;
    }

    @ExceptionHandler (MovieAlreadyExistsException.class)
    ProblemDetail handleMovieNotFound (MovieAlreadyExistsException exception) {
        ProblemDetail detail = forStatusAndDetail(CONFLICT, exception.getMessage());
        detail.setTitle("Conflict, Movie already exists");
        detail.setType(URI.create(RUNTIME_EXCEPTION_URL));
        detail.setProperty(CATEGORY, "Movie Already Exists");
        detail.setProperty(TIMESTAMP, Instant.now());
        log.error(exception.getMessage(), exception);
        return detail;
    }
}
