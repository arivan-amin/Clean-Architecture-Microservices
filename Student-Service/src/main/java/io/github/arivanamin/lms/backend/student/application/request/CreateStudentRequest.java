package io.github.arivanamin.lms.backend.student.application.request;

import io.github.arivanamin.lms.backend.core.domain.dates.TimestampHelper;
import io.github.arivanamin.lms.backend.core.domain.gender.Gender;
import io.github.arivanamin.lms.backend.student.domain.entity.Student;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentRequest {

    String firstName;
    String lastName;
    String email;
    long dateOfBirth;
    Gender gender;
    String address;

    public Student toDomainEntity () {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setDateOfBirth(convertTimestampToLocalDate());
        student.setGender(gender);
        student.setAddress(address);
        return student;
    }

    private LocalDate convertTimestampToLocalDate () {
        return TimestampHelper.toLocalDateTime(dateOfBirth)
            .toLocalDate();
    }
}
