package io.github.arivanamin.lms.backend.student.application.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public final class StudentCaches {

    public static final String GET_ALL_STUDENTS_CACHE = "studentsCache";
    public static final String GET_STUDENT_BY_ID_CACHE = "studentByIdCache";
}
