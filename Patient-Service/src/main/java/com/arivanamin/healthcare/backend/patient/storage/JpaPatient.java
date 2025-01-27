package com.arivanamin.healthcare.backend.patient.storage;

import com.arivanamin.healthcare.backend.base.domain.gender.Gender;
import com.arivanamin.healthcare.backend.patient.core.entity.Patient;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table (name = "patients")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class JpaPatient {

    @Id
    @UuidGenerator
    UUID id;

    @NotBlank
    String firstName;

    @NotBlank
    String lastName;

    @Email
    String email;

    @Past
    LocalDate dateOfBirth;

    @NotNull
    Gender gender;

    @NotBlank
    String address;

    public static JpaPatient fromDomain (Patient patient) {
        return new ModelMapper().map(patient, JpaPatient.class);
    }

    public Patient toDomain () {
        return new ModelMapper().map(this, Patient.class);
    }
}
