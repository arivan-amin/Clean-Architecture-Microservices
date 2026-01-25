package io.github.arivanamin.lms.backend.patient.application.request;

import io.github.arivanamin.lms.backend.base.core.gender.Gender;
import io.github.arivanamin.lms.backend.patient.core.entity.Patient;

import java.util.UUID;

import static io.github.arivanamin.lms.backend.base.core.dates.TimestampHelper.toLocalDateTime;

public record UpdatePatientRequest(String firstName, String lastName, String email,
    long dateOfBirth, Gender gender, String address) {

    public Patient toEntity (UUID id) {
        Patient patient = new Patient();
        patient.setId(id);
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setEmail(email);
        patient.setDateOfBirth(toLocalDateTime(dateOfBirth).toLocalDate());
        patient.setGender(gender);
        patient.setAddress(address);
        return patient;
    }
}
