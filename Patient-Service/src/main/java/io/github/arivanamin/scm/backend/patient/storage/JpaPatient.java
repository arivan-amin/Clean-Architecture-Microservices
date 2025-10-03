package io.github.arivanamin.scm.backend.patient.storage;

import io.github.arivanamin.scm.backend.base.core.gender.Gender;
import io.github.arivanamin.scm.backend.outbox.storage.audit.StorageAuditData;
import io.github.arivanamin.scm.backend.patient.core.entity.Patient;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
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
@FieldDefaults (level = AccessLevel.PRIVATE)
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
