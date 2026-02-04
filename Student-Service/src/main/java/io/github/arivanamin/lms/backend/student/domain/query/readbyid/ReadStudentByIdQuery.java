package io.github.arivanamin.lms.backend.student.domain.query.readbyid;

import io.github.arivanamin.lms.backend.student.domain.entity.Student;
import io.github.arivanamin.lms.backend.student.domain.exception.StudentNotFoundException;
import io.github.arivanamin.lms.backend.student.domain.persistence.StudentStorage;
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
