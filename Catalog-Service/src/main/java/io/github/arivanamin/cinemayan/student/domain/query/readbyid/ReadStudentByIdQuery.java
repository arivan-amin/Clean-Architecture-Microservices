package io.github.arivanamin.cinemayan.student.domain.query.readbyid;

import io.github.arivanamin.cinemayan.student.domain.entity.Student;
import io.github.arivanamin.cinemayan.student.domain.exception.StudentNotFoundException;
import io.github.arivanamin.cinemayan.student.domain.persistence.StudentStorage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReadStudentByIdQuery {

    private final StudentStorage storage;

    public ReadStudentByIdOutput execute (ReadStudentByIdInput input) {
        Student student = storage.findById(input.getId())
            .orElseThrow(StudentNotFoundException::new);
        return new ReadStudentByIdOutput(student);
    }
}
