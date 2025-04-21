package io.github.arivanamin.scm.backend.patient.application.request;

import io.github.arivanamin.scm.backend.base.domain.gender.Gender;
import io.github.arivanamin.scm.backend.patient.core.entity.Patient;
import lombok.*;
import org.modelmapper.ModelMapper;

import static io.github.arivanamin.scm.backend.base.domain.dates.TimestampHelper.toLocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePatientRequest {

    String firstName;
    String lastName;
    String email;
    long dateOfBirth;
    Gender gender;
    String address;

    public Patient toEntity () {
        Patient patient = new ModelMapper().map(this, Patient.class);
        patient.setDateOfBirth(toLocalDateTime(dateOfBirth).toLocalDate());
        return patient;
    }
}
