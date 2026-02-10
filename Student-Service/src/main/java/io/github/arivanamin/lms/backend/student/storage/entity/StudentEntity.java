package io.github.arivanamin.lms.backend.student.storage.entity;

import io.github.arivanamin.lms.backend.core.domain.gender.Gender;
import io.github.arivanamin.lms.backend.outbox.storage.audit.AuditFields;
import io.github.arivanamin.lms.backend.student.domain.entity.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

import static jakarta.persistence.EnumType.STRING;

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
    @Column (name = "id")
    UUID id;

    @NotBlank
    @Column (name = "first_name")
    String firstName;

    @NotBlank
    @Column (name = "last_name")
    String lastName;

    @Email
    @Column (name = "email", nullable = false)
    String email;

    @NotBlank
    @Column (name = "phone_number")
    String phoneNumber;

    @Past
    @Column (name = "date_of_birth", nullable = false)
    LocalDate dateOfBirth;

    @Enumerated (STRING)
    @NotNull
    @Column (name = "gender")
    Gender gender;

    @Enumerated (STRING)
    @NotNull
    @Column (name = "status")
    StudentStatus status;

    @Enumerated (STRING)
    @NotNull
    @Column (name = "grade_level")
    GradeLevel gradeLevel;

    @NotBlank
    @Column (name = "address")
    String address;

    public static StudentEntity fromDomain (Student domain) {
        return new StudentEntity(domain.getId(), domain.getFirstName(), domain.getLastName(),
            domain.getEmail(), domain.getPhoneNumber(), domain.getDateOfBirth(), domain.getGender(),
            domain.getStatus(), domain.getGradeLevel(), domain.getAddress());
    }

    public Student toDomain () {
        return new Student(id, firstName, lastName, email, phoneNumber, dateOfBirth, gender, status,
            gradeLevel, address, getCreatedAt(), getUpdatedAt());
    }
}
