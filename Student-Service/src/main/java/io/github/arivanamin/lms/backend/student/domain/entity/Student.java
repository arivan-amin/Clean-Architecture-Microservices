package io.github.arivanamin.lms.backend.student.domain.entity;

import io.github.arivanamin.lms.backend.core.domain.gender.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults (level = AccessLevel.PRIVATE)
public class Student {

    UUID id;
    String firstName;
    String lastName;
    String email;
    LocalDate dateOfBirth;
    Gender gender;
    String address;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
