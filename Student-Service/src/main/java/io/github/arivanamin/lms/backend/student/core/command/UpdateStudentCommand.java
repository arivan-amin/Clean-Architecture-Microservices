package io.github.arivanamin.lms.backend.student.core.command;

import io.github.arivanamin.lms.backend.student.core.entity.Student;
import io.github.arivanamin.lms.backend.student.core.persistence.StudentStorage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateStudentCommand {

    private final StudentStorage storage;

    public void execute (Student student) {
        storage.update(student);
    }
}
