package io.github.arivanamin.scm.backend.patient;

import io.github.arivanamin.scm.backend.base.core.gender.Gender;
import io.github.arivanamin.scm.backend.patient.core.entity.Patient;
import io.github.arivanamin.scm.backend.testing.architecture.bases.BaseUnitTest;
import org.instancio.Instancio;

import java.time.LocalDate;
import java.util.List;

public class PatientTestingUtility implements BaseUnitTest {

    public static List<Patient> createSamplePatientsList () {
        return List.of(createSamplePatient(), createSamplePatient(), createSamplePatient());
    }

    public static Patient createSamplePatient () {
        Patient entity = new Patient();
        entity.setFirstName(FAKER.elderScrolls()
            .firstName());
        entity.setLastName(FAKER.elderScrolls()
            .lastName());
        entity.setEmail(FAKER.internet()
            .emailAddress());
        entity.setDateOfBirth(LocalDate.now()
            .minusYears(25));
        entity.setGender(Instancio.create(Gender.class));
        entity.setAddress(FAKER.address()
            .fullAddress());
        return entity;
    }
}
