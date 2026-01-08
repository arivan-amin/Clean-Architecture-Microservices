package io.github.arivanamin.scm.backend.patient;

import io.github.arivanamin.scm.backend.base.core.gender.Gender;
import io.github.arivanamin.scm.backend.patient.core.entity.Patient;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public final class PatientTestData {

    public static List<Patient> patientsList () {
        return List.of(withEmail("clint.eastwood@example.com"),
            withEmail("anne.hathaway@example.com"), withEmail("kate.wislet@example.com"));
    }

    public static Patient withEmail (String email) {
        Patient entity = new Patient();
        entity.setFirstName("Brad");
        entity.setLastName("Pitt");
        entity.setEmail(email);
        entity.setDateOfBirth(LocalDate.now()
            .minusYears(25));
        entity.setGender(Gender.MALE);
        entity.setAddress("Colorado, Denver 77th avenue");
        return entity;
    }

    public static Patient withDefaultEmail () {
        return withEmail("emma.stone@example.com");
    }
}
