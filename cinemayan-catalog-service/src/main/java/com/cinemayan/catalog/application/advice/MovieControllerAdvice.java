package com.cinemayan.catalog.application.advice;

import com.cinemayan.catalog.domain.movie.exception.MovieAlreadyExistsException;
import com.cinemayan.catalog.domain.movie.exception.MovieNotFoundException;
import com.cinemayan.core.application.advice.ProblemDetailFactory;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.cinemayan.core.application.advice.ProblemDetailCategories.RESOURCE_CONFLICT;
import static com.cinemayan.core.application.advice.ProblemDetailCategories.RESOURCE_NOT_FOUND;
import static com.cinemayan.core.application.advice.ProblemDetailExceptionUrls.RUNTIME_EXCEPTION_URL;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public final class MovieControllerAdvice {

    private final ProblemDetailFactory factory;

    @ExceptionHandler (MovieNotFoundException.class)
    ProblemDetail handleMovieNotFound (MovieNotFoundException exception,
                                       HttpServletRequest request) {
        log.warn("Movie not found Exception [movieID = {}, errorCode = {}, URI = {}]",
            exception.getMovieId(), exception.getErrorCode(), request.getRequestURI());

        String title = "Resource Not Found";
        String detail = "Movie with ID: %s, Not Found".formatted(exception.getMovieId());
        return factory.build(NOT_FOUND, title, detail, RESOURCE_NOT_FOUND, RUNTIME_EXCEPTION_URL);
    }

    @ExceptionHandler (MovieAlreadyExistsException.class)
    ProblemDetail handleMovieAlreadyExists (MovieAlreadyExistsException exception,
                                            HttpServletRequest request) {
        log.warn("Movie already exists [title = {}, errorCode = {}, URI = {}]",
            exception.getMovieTitle(), exception.getErrorCode(), request.getRequestURI());

        String title = "Resource Conflict";
        String detail = "Movie with title '%s' already exists".formatted(exception.getMovieTitle());
        return factory.build(CONFLICT, title, detail, RESOURCE_CONFLICT, RUNTIME_EXCEPTION_URL);
    }
}
