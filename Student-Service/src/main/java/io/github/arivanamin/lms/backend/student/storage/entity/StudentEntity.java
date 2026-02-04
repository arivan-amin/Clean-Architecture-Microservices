package io.github.arivanamin.lms.backend.student.storage.entity;

import io.github.arivanamin.lms.backend.core.domain.gender.Gender;
import io.github.arivanamin.lms.backend.outbox.storage.audit.AuditFields;
import io.github.arivanamin.lms.backend.student.domain.entity.Student;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table (name = "students")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults (level = AccessLevel.PRIVATE)
@ToString (callSuper = true)
public class StudentEntity extends AuditFields {

    @Id
    @UuidGenerator
    UUID id;

    @NotBlank
    String firstName;

    @NotBlank
    String lastName;

    @Email
    @Column (nullable = false)
    String email;

    @Past
    @Column (nullable = false)
    LocalDate dateOfBirth;

    @NotNull
    Gender gender;

    @NotBlank
    String address;

    public static StudentEntity fromDomain (Student domain) {
        return new StudentEntity(domain.getId(), domain.getFirstName(), domain.getLastName(),
            domain.getEmail(), domain.getDateOfBirth(), domain.getGender(), domain.getAddress());
    }

    public Student toDomain () {
        return new Student(id, firstName, lastName, email, dateOfBirth, gender, address,
            getCreatedAt(), getUpdatedAt());
    }
}
