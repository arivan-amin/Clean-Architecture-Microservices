package io.github.arivanamin.cam.backend.patient.core.entity;

import io.github.arivanamin.cam.backend.base.domain.gender.Gender;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    UUID id;
    String firstName;
    String lastName;
    String email;
    LocalDate dateOfBirth;
    Gender gender;
    String address;
}
