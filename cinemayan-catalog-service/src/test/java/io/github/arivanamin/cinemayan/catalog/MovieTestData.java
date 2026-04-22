package io.github.arivanamin.cinemayan.catalog;

import io.github.arivanamin.cinemayan.catalog.domain.entity.*;
import io.github.arivanamin.cinemayan.core.domain.gender.Gender;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public final class MovieTestData {

    public static List<Movie> moviesList () {
        return List.of(withEmail("clint.eastwood@example.com"),
            withEmail("anne.hathaway@example.com"), withEmail("kate.wislet@example.com"));
    }

    public static Movie withEmail (String email) {
        Movie entity = new Movie();
        entity.setFirstName("Brad");
        entity.setLastName("Pitt");
        entity.setEmail(email);
        entity.setPhoneNumber("07701234567");
        entity.setDateOfBirth(LocalDate.now()
            .minusYears(25));
        entity.setGender(Gender.MALE);
        entity.setStatus(StudentStatus.ENROLLED);
        entity.setGradeLevel(GradeLevel.GRADE_5);
        entity.setAddress("Colorado, Denver 77th avenue");
        return entity;
    }

    public static Movie withDefaultEmail () {
        return withEmail("emma.stone@example.com");
    }
}
