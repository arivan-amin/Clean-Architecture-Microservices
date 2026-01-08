package io.github.arivanamin.scm.backend.patient.storage;

import io.github.arivanamin.scm.backend.base.core.gender.Gender;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public final class JpaPatientTestData {

    public static JpaPatient defaultPatient () {
        JpaPatient entity = new JpaPatient();
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
