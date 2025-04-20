package io.github.arivanamin.cam.backend.patient.application.response;

import io.github.arivanamin.cam.backend.base.domain.gender.Gender;
import io.github.arivanamin.cam.backend.patient.core.entity.Patient;
import lombok.*;
import org.modelmapper.ModelMapper;

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
        return new ModelMapper().map(patient, PatientResponse.class);
    }
}
