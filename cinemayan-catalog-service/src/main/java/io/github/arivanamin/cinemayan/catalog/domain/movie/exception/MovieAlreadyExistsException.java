package io.github.arivanamin.cinemayan.catalog.domain.movie.exception;

public class MovieAlreadyExistsException extends RuntimeException {

    public MovieAlreadyExistsException (String title) {
        super("Movie with the requested title: %s already exists".formatted(title));
    }
}
