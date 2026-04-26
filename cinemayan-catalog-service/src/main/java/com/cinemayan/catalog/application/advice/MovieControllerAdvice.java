package com.cinemayan.catalog.application.advice;

import com.cinemayan.catalog.domain.movie.exception.MovieAlreadyExistsException;
import com.cinemayan.catalog.domain.movie.exception.MovieNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.Clock;
import java.time.Instant;

import static com.cinemayan.core.application.advice.ProblemDetailCategories.RESOURCE_CONFLICT;
import static com.cinemayan.core.application.advice.ProblemDetailCategories.RESOURCE_NOT_FOUND;
import static com.cinemayan.core.application.advice.ProblemDetailExceptionUrls.RUNTIME_EXCEPTION_URL;
import static com.cinemayan.core.application.advice.ProblemDetailProperties.*;
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
        log.warn("Movie not found Exception [movieID = {}, errorCode = {}, URI = {}]",
            exception.getMovieId(), exception.getErrorCode(), request.getRequestURI());
        
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(NOT_FOUND, "Movie Not Found");
        detail.setTitle("Requested Movie Not Found");
        detail.setType(URI.create(RUNTIME_EXCEPTION_URL));
        detail.setProperty(CATEGORY, RESOURCE_NOT_FOUND);
        detail.setProperty(TIMESTAMP, Instant.now(clock));
        detail.setProperty(TRACE_ID_KEY, MDC.get(TRACE_ID_VALUE));
        detail.setProperty(SPAN_ID_KEY, MDC.get(SPAN_ID_VALUE));
        return detail;
    }

    @ResponseStatus (CONFLICT)
    @ExceptionHandler (MovieAlreadyExistsException.class)
    ProblemDetail handleMovieAlreadyExists (MovieAlreadyExistsException exception,
                                            HttpServletRequest request) {
        log.warn("Movie already exists [title = {}, errorCode = {}, URI = {}]",
            exception.getMovieTitle(), exception.getErrorCode(), request.getRequestURI(),
            exception);

        ProblemDetail detail = ProblemDetail.forStatusAndDetail(CONFLICT, "Movie already exists");
        detail.setTitle("Conflict, Movie already exists");
        detail.setType(URI.create(RUNTIME_EXCEPTION_URL));
        detail.setProperty(CATEGORY, RESOURCE_CONFLICT);
        detail.setProperty(TIMESTAMP, Instant.now(clock));
        detail.setProperty(TRACE_ID_KEY, MDC.get(TRACE_ID_VALUE));
        detail.setProperty(SPAN_ID_KEY, MDC.get(SPAN_ID_VALUE));
        return detail;
    }
}
