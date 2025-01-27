package com.arivanamin.healthcare.backend.patient.application.request;

import com.arivanamin.healthcare.backend.base.domain.gender.Gender;
import com.arivanamin.healthcare.backend.patient.core.entity.Patient;
import lombok.Value;
import org.modelmapper.ModelMapper;

import java.util.UUID;

import static com.arivanamin.healthcare.backend.base.domain.dates.TimestampHelper.toLocalDateTime;

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
