package io.github.arivanamin.cinemayan.catalog.domain.movie.entity;

import io.github.arivanamin.cinemayan.core.domain.gender.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults (level = AccessLevel.PRIVATE)
public class Movie {

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
}
