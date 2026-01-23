package io.github.arivanamin.lms.backend.patient.application.response;

import io.github.arivanamin.lms.backend.base.core.gender.Gender;
import io.github.arivanamin.lms.backend.patient.core.entity.Patient;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponse {

    UUID id;
    String firstName;
    String lastName;
    String email;
    LocalDate dateOfBirth;
    Gender gender;
    String address;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public static PatientResponse of (Patient patient) {
        return new PatientResponse(patient.getId(), patient.getFirstName(), patient.getLastName(),
            patient.getEmail(), patient.getDateOfBirth(), patient.getGender(), patient.getAddress(),
            patient.getCreatedAt(), patient.getUpdatedAt());
    }
}
