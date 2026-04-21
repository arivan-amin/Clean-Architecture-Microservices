package io.github.arivanamin.cinemayan.student.domain.command.create;

import io.github.arivanamin.cinemayan.student.domain.entity.Student;
import io.github.arivanamin.cinemayan.student.domain.exception.StudentAlreadyExistsException;
import io.github.arivanamin.cinemayan.student.domain.persistence.StudentStorage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateStudentCommand {

    private final StudentStorage storage;

    public CreateStudentOutput execute (CreateStudentInput input) {
        Student student = input.getStudent();
        if (studentAlreadyExists(student)) {
            throw new StudentAlreadyExistsException(student.getEmail());
        }
        return new CreateStudentOutput(storage.create(student));
    }

    private boolean studentAlreadyExists (Student student) {
        return storage.findByEmail(student.getEmail())
            .isPresent();
    }
}
