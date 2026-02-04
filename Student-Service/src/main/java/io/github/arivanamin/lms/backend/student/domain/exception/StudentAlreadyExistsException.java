package io.github.arivanamin.lms.backend.student.domain.exception;

public class StudentAlreadyExistsException extends RuntimeException {

    public StudentAlreadyExistsException (String email) {
        super("Student with the requested email: %s already exists".formatted(email));
    }
}
