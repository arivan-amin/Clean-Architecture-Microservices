package io.github.arivanamin.lms.backend.student.core.query;

import io.github.arivanamin.lms.backend.student.core.entity.Student;
import io.github.arivanamin.lms.backend.student.core.exception.StudentNotFoundException;
import io.github.arivanamin.lms.backend.student.core.persistence.StudentStorage;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class ReadStudentByIdQuery {

    private final StudentStorage storage;

    public Student execute (UUID id) {
        return storage.findById(id)
            .orElseThrow(StudentNotFoundException::new);
    }
}
