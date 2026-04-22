package io.github.arivanamin.cinemayan.catalog.domain.movie.command.create;

import io.github.arivanamin.cinemayan.catalog.domain.movie.entity.Movie;
import io.github.arivanamin.cinemayan.catalog.domain.movie.exception.MovieAlreadyExistsException;
import io.github.arivanamin.cinemayan.catalog.domain.movie.storage.MovieStorage;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class CreateMovieCommand {

    private final MovieStorage storage;

    public Output execute (Input input) {
        Movie student = input.movie();
        if (studentAlreadyExists(student)) {
            throw new MovieAlreadyExistsException(student.getEmail());
        }
        return new Output(storage.create(student));
    }

    private boolean studentAlreadyExists (Movie student) {
        return storage.findByEmail(student.getEmail())
            .isPresent();
    }

    public record Input(@NotNull Movie movie) {

    }

    public record Output(UUID movieId) {

    }
}
