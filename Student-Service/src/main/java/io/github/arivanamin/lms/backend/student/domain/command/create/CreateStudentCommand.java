package io.github.arivanamin.lms.backend.student.domain.command.create;

import io.github.arivanamin.lms.backend.student.domain.entity.Student;
import io.github.arivanamin.lms.backend.student.domain.exception.StudentAlreadyExistsException;
import io.github.arivanamin.lms.backend.student.domain.persistence.StudentStorage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateStudentCommand {

    private final StudentStorage storage;

    public CreateStudentCommandOutput execute (CreateStudentCommandInput input) {
        Student student = input.getStudent();
        if (studentAlreadyExists(student)) {
            throw new StudentAlreadyExistsException(student.getEmail());
        }
        return new CreateStudentCommandOutput(storage.create(student));
    }

    private boolean studentAlreadyExists (Student student) {
        return storage.findByEmail(student.getEmail())
            .isPresent();
    }
}
