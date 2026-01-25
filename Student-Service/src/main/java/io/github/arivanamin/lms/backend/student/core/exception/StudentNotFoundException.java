package io.github.arivanamin.lms.backend.student.core.exception;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException () {
        super("Student by the requested id not found");
    }
}
