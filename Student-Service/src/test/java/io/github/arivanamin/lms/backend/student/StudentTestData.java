package io.github.arivanamin.lms.backend.student;

import io.github.arivanamin.lms.backend.core.domain.gender.Gender;
import io.github.arivanamin.lms.backend.student.domain.entity.Student;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public final class StudentTestData {

    public static List<Student> studentsList () {
        return List.of(withEmail("clint.eastwood@example.com"),
            withEmail("anne.hathaway@example.com"), withEmail("kate.wislet@example.com"));
    }

    public static Student withEmail (String email) {
        Student entity = new Student();
        entity.setFirstName("Brad");
        entity.setLastName("Pitt");
        entity.setEmail(email);
        entity.setDateOfBirth(LocalDate.now()
            .minusYears(25));
        entity.setGender(Gender.MALE);
        entity.setAddress("Colorado, Denver 77th avenue");
        return entity;
    }

    public static Student withDefaultEmail () {
        return withEmail("emma.stone@example.com");
    }
}
