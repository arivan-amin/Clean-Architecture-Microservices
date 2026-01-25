package io.github.arivanamin.lms.backend.student.core.exception;

public class StudentAlreadyExistsException extends RuntimeException {

    public StudentAlreadyExistsException () {
        super("student with the requested email already exists");
    }
}
