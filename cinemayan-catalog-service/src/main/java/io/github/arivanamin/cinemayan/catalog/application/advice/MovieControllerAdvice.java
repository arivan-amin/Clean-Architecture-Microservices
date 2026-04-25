package io.github.arivanamin.cinemayan.catalog.application.advice;

import io.github.arivanamin.cinemayan.catalog.domain.movie.exception.MovieAlreadyExistsException;
import io.github.arivanamin.cinemayan.catalog.domain.movie.exception.MovieNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.Clock;
import java.time.Instant;

import static io.github.arivanamin.cinemayan.core.application.advice.ProblemDetailCategories.RESOURCE_NOT_FOUND;
import static io.github.arivanamin.cinemayan.core.application.advice.ProblemDetailExceptionUrls.RUNTIME_EXCEPTION_URL;
import static io.github.arivanamin.cinemayan.core.application.advice.ProblemDetailProperties.*;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public final class MovieControllerAdvice {

    private final Clock clock;

    @ResponseStatus (NOT_FOUND)
    @ExceptionHandler (MovieNotFoundException.class)
    ProblemDetail handleMovieNotFound (MovieNotFoundException exception,
                                       HttpServletRequest request) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(NOT_FOUND,
            "Requested Movie Not Found by the supplied Movie Id");
        detail.setTitle("Requested Movie Not Found");
        detail.setType(URI.create(RUNTIME_EXCEPTION_URL));
        detail.setProperty(CATEGORY, RESOURCE_NOT_FOUND);
        detail.setProperty(TIMESTAMP, Instant.now(clock));
        detail.setProperty(TRACE_ID_KEY, TRACE_ID_VALUE);
        detail.setProperty(SPAN_ID_KEY, SPAN_ID_VALUE);
        log.error("Movie not found Exception", exception);
        return detail;
    }

    @ResponseStatus (CONFLICT)
    @ExceptionHandler (MovieAlreadyExistsException.class)
    ProblemDetail handleMovieNotFound (MovieAlreadyExistsException exception) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(CONFLICT, "Movie already exists");
        detail.setTitle("Conflict, Movie already exists");
        detail.setType(URI.create(RUNTIME_EXCEPTION_URL));
        detail.setProperty(CATEGORY, "Movie Already Exists");
        detail.setProperty(TIMESTAMP, Instant.now(clock));
        detail.setProperty(TRACE_ID_KEY, TRACE_ID_VALUE);
        detail.setProperty(SPAN_ID_KEY, SPAN_ID_VALUE);
        log.warn("Movie already exists", exception);
        return detail;
    }
}
