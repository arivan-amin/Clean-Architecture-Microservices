package io.github.arivanamin.lms.backend.patient.application.request;

import io.github.arivanamin.lms.backend.base.core.dates.TimestampHelper;
import io.github.arivanamin.lms.backend.base.core.gender.Gender;
import io.github.arivanamin.lms.backend.patient.core.entity.Patient;
import lombok.*;

import java.time.LocalDate;

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

    public Patient toDomainEntity () {
        Patient patient = new Patient();
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setEmail(email);
        patient.setDateOfBirth(convertTimestampToLocalDate());
        patient.setGender(gender);
        patient.setAddress(address);
        return patient;
    }

    private LocalDate convertTimestampToLocalDate () {
        return TimestampHelper.toLocalDateTime(dateOfBirth)
            .toLocalDate();
    }
}
