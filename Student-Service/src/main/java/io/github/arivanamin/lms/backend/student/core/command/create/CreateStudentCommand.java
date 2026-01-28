package io.github.arivanamin.lms.backend.student.core.command.create;

import io.github.arivanamin.lms.backend.student.core.entity.Student;
import io.github.arivanamin.lms.backend.student.core.exception.StudentAlreadyExistsException;
import io.github.arivanamin.lms.backend.student.core.persistence.StudentStorage;
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
