package io.github.arivanamin.cam.backend.patient.application.request;

import io.github.arivanamin.cam.backend.base.domain.gender.Gender;
import io.github.arivanamin.cam.backend.patient.core.entity.Patient;
import lombok.Value;
import org.modelmapper.ModelMapper;

import java.util.UUID;

import static io.github.arivanamin.cam.backend.base.domain.dates.TimestampHelper.toLocalDateTime;

@Value
public class UpdatePatientRequest {

    String firstName;
    String lastName;
    String email;
    long dateOfBirth;
    Gender gender;
    String address;

    public Patient toEntity (UUID id) {
        Patient patient = new ModelMapper().map(this, Patient.class);
        patient.setId(id);
        patient.setDateOfBirth(toLocalDateTime(dateOfBirth).toLocalDate());
        return patient;
    }
}
