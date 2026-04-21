package io.github.arivanamin.cinemayan.catalog.infrastructure;

import io.github.arivanamin.cinemayan.catalog.infrastructure.entity.StudentEntity;
import io.github.arivanamin.cinemayan.core.domain.gender.Gender;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public final class JpaStudentTestData {

    public static StudentEntity defaultStudent () {
        StudentEntity entity = new StudentEntity();
        entity.setFirstName("Michale");
        entity.setLastName("Fowler");
        entity.setEmail("michale.fowler@example.com");
        entity.setDateOfBirth(LocalDate.now()
            .minusYears(25));
        entity.setGender(Gender.FEMALE);
        entity.setAddress("Portland, Oregon 15th avenue");
        return entity;
    }
}
