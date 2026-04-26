package io.github.arivanamin.cinemayan.catalog;

import io.github.arivanamin.cinemayan.catalog.domain.movie.entity.Movie;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public final class MovieTestData {

    public static List<Movie> moviesList () {
        return List.of(withEmail("clint.eastwood@example.com"),
            withEmail("anne.hathaway@example.com"), withEmail("kate.wislet@example.com"));
    }

    public static Movie withEmail (String email) {
        Movie entity = new Movie(null);
        return entity;
    }

    public static Movie withDefaultEmail () {
        return withEmail("emma.stone@example.com");
    }
}
