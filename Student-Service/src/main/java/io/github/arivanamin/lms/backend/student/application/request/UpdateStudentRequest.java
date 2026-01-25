package io.github.arivanamin.lms.backend.student.application.request;

import io.github.arivanamin.lms.backend.base.core.gender.Gender;
import io.github.arivanamin.lms.backend.student.core.entity.Student;

import java.util.UUID;

import static io.github.arivanamin.lms.backend.base.core.dates.TimestampHelper.toLocalDateTime;

public record UpdateStudentRequest(String firstName, String lastName, String email,
    long dateOfBirth, Gender gender, String address) {

    public Student toEntity (UUID id) {
        Student student = new Student();
        student.setId(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setDateOfBirth(toLocalDateTime(dateOfBirth).toLocalDate());
        student.setGender(gender);
        student.setAddress(address);
        return student;
    }
}
