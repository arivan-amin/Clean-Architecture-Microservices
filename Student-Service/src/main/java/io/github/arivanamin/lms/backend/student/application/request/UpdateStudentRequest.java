package io.github.arivanamin.lms.backend.student.application.request;

import io.github.arivanamin.lms.backend.core.domain.gender.Gender;
import io.github.arivanamin.lms.backend.student.domain.entity.*;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class UpdateStudentRequest {

    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    LocalDate dateOfBirth;
    Gender gender;
    StudentStatus status;
    GradeLevel gradeLevel;
    String address;

    public Student toEntity (UUID id) {
        Student student = new Student();
        student.setId(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setPhoneNumber(phoneNumber);
        student.setDateOfBirth(dateOfBirth);
        student.setGender(gender);
        student.setStatus(status);
        student.setGradeLevel(gradeLevel);
        student.setAddress(address);
        return student;
    }
}
