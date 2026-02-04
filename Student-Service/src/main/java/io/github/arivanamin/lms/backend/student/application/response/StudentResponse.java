package io.github.arivanamin.lms.backend.student.application.response;

import io.github.arivanamin.lms.backend.core.domain.gender.Gender;
import io.github.arivanamin.lms.backend.student.domain.entity.Student;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {

    UUID id;
    String firstName;
    String lastName;
    String email;
    LocalDate dateOfBirth;
    Gender gender;
    String address;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public static StudentResponse of (Student student) {
        return new StudentResponse(student.getId(), student.getFirstName(), student.getLastName(),
            student.getEmail(), student.getDateOfBirth(), student.getGender(), student.getAddress(),
            student.getCreatedAt(), student.getUpdatedAt());
    }
}
