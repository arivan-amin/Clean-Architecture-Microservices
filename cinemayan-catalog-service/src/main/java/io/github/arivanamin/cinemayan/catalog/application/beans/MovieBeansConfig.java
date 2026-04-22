package io.github.arivanamin.cinemayan.catalog.application.beans;

import io.github.arivanamin.cinemayan.catalog.domain.movie.command.create.CreateMovieCommand;
import io.github.arivanamin.cinemayan.catalog.domain.movie.storage.MovieStorage;
import io.github.arivanamin.cinemayan.catalog.infrastructure.storage.movie.MovieJpaStorage;
import io.github.arivanamin.cinemayan.catalog.infrastructure.storage.movie.MovieRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class MovieBeansConfig {

    @Bean
    public MovieStorage movieStorage (MovieRepository repository) {
        return new MovieJpaStorage(repository);
    }

    @Bean
    public CreateMovieCommand createMovieCommand (MovieStorage storage) {
        return new CreateMovieCommand(storage);
    }
}
