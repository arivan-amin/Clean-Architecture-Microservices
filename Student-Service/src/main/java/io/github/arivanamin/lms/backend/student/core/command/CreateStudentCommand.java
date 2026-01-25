package io.github.arivanamin.lms.backend.student.core.command;

import io.github.arivanamin.lms.backend.student.core.entity.Student;
import io.github.arivanamin.lms.backend.student.core.exception.StudentAlreadyExistsException;
import io.github.arivanamin.lms.backend.student.core.persistence.StudentStorage;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class CreateStudentCommand {

    private final StudentStorage storage;

    public UUID execute (Student student) {
        if (doesStudentExist(student)) {
            throw new StudentAlreadyExistsException();
        }
        return storage.create(student);
    }

    private boolean doesStudentExist (Student student) {
        return storage.findByEmail(student.getEmail())
            .isPresent();
    }
}
