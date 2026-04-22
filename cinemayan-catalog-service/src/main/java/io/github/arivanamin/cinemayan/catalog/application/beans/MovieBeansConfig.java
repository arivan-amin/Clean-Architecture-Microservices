package io.github.arivanamin.cinemayan.catalog.application.beans;

import io.github.arivanamin.cinemayan.catalog.domain.command.delete.DeleteStudentCommand;
import io.github.arivanamin.cinemayan.catalog.domain.command.update.UpdateStudentCommand;
import io.github.arivanamin.cinemayan.catalog.domain.movie.command.create.CreateStudentCommand;
import io.github.arivanamin.cinemayan.catalog.domain.movie.storage.MovieStorage;
import io.github.arivanamin.cinemayan.catalog.domain.query.readbyid.ReadStudentByIdQuery;
import io.github.arivanamin.cinemayan.catalog.domain.query.readbyspec.ReadStudentsQuery;
import io.github.arivanamin.cinemayan.catalog.infrastructure.storage.movie.MovieJpaStorage;
import io.github.arivanamin.cinemayan.catalog.infrastructure.storage.movie.MovieRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class MovieBeansConfig {

    @Bean
    public MovieStorage storage (MovieRepository repository) {
        return new MovieJpaStorage(repository);
    }

    @Bean
    public ReadStudentsQuery readMoviesQuery (MovieStorage storage) {
        return new ReadStudentsQuery(storage);
    }

    @Bean
    public ReadStudentByIdQuery readMovieByIdQuery (MovieStorage storage) {
        return new ReadStudentByIdQuery(storage);
    }

    @Bean
    public CreateStudentCommand createMovieCommand (MovieStorage storage) {
        return new CreateStudentCommand(storage);
    }

    @Bean
    public UpdateStudentCommand updateMovieCommand (MovieStorage storage) {
        return new UpdateStudentCommand(storage);
    }

    @Bean
    public DeleteStudentCommand deleteMovieCommand (MovieStorage storage) {
        return new DeleteStudentCommand(storage);
    }
}
