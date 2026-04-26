package io.github.arivanamin.cinemayan.catalog.domain.movie.exception;

import io.github.arivanamin.cinemayan.catalog.domain.movie.error.MovieErrorCode;
import lombok.Getter;

@Getter
public class MovieAlreadyExistsException extends MovieException {

    private final String movieTitle;

    public MovieAlreadyExistsException (String movieTitle) {
        super("Movie with title: %s already exists".formatted(movieTitle),
            MovieErrorCode.MOVIE_ALREADY_EXISTS);
        this.movieTitle = movieTitle;
    }

    public MovieAlreadyExistsException (String movieTitle, Throwable cause) {
        super("Movie with title: %s already exists".formatted(movieTitle), cause,
            MovieErrorCode.MOVIE_ALREADY_EXISTS);
        this.movieTitle = movieTitle;
    }
}
