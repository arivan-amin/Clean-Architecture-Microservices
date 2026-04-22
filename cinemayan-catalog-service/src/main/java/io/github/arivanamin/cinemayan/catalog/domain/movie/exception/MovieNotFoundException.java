package io.github.arivanamin.cinemayan.catalog.domain.movie.exception;

public class MovieNotFoundException extends RuntimeException {

    public MovieNotFoundException () {
        super("Movie by the requested id not found");
    }
}
