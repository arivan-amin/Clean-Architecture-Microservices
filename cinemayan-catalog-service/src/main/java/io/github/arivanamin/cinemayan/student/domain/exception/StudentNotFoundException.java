package io.github.arivanamin.cinemayan.student.domain.exception;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException () {
        super("Student by the requested id not found");
    }
}
