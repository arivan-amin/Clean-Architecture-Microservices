package io.github.arivanamin.lms.backend.student.application.response;

import io.github.arivanamin.lms.backend.core.domain.gender.Gender;
import io.github.arivanamin.lms.backend.student.domain.entity.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {

    UUID id;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    LocalDate dateOfBirth;
    Gender gender;
    StudentStatus status;
    GradeLevel gradeLevel;
    String address;
    Instant createdAt;
    Instant updatedAt;

    // todo 2/8/26 - handle timezone and return date as long
    public static StudentResponse of (Student student) {
        return new StudentResponse(student.getId(), student.getFirstName(), student.getLastName(),
            student.getEmail(), student.getPhoneNumber(), student.getDateOfBirth(),
            student.getGender(), student.getStatus(), student.getGradeLevel(), student.getAddress(),
            student.getCreatedAt(), student.getUpdatedAt());
    }
}
