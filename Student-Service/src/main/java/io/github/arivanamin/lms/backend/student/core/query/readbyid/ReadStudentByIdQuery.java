package io.github.arivanamin.lms.backend.student.core.query.readbyid;

import io.github.arivanamin.lms.backend.student.core.entity.Student;
import io.github.arivanamin.lms.backend.student.core.exception.StudentNotFoundException;
import io.github.arivanamin.lms.backend.student.core.persistence.StudentStorage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReadStudentByIdQuery {

    private final StudentStorage storage;

    public ReadStudentByIdQueryOutput execute (ReadStudentByIdQueryInput input) {
        Student student = storage.findById(input.getId())
            .orElseThrow(StudentNotFoundException::new);
        return new ReadStudentByIdQueryOutput(student);
    }
}
