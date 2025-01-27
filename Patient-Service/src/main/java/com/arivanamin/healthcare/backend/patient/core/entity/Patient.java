package com.arivanamin.healthcare.backend.patient.core.entity;

import com.arivanamin.healthcare.backend.base.domain.gender.Gender;
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
