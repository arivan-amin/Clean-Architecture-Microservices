package io.github.arivanamin.lms.backend.student.storage;

import io.github.arivanamin.lms.backend.base.core.gender.Gender;
import io.github.arivanamin.lms.backend.student.storage.entity.StudentEntity;
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
