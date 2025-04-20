package io.github.arivanamin.cam.backend.patient.storage;

import io.github.arivanamin.cam.backend.base.domain.gender.Gender;
import io.github.arivanamin.cam.backend.base.domain.persistence.StorageAuditData;
import io.github.arivanamin.cam.backend.patient.core.entity.Patient;
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
@ToString (callSuper = true)
public class JpaPatient extends StorageAuditData {

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
