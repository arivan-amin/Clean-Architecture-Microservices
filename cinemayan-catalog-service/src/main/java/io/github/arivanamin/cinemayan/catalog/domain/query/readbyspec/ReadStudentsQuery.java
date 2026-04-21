package io.github.arivanamin.cinemayan.catalog.domain.query.readbyspec;

import io.github.arivanamin.cinemayan.catalog.domain.entity.Student;
import io.github.arivanamin.cinemayan.catalog.domain.persistence.StudentStorage;
import io.github.arivanamin.cinemayan.core.domain.pagination.PaginatedResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReadStudentsQuery {

    private final StudentStorage storage;

    public ReadStudentsOutput execute (ReadStudentsInput input) {
        PaginatedResponse<Student> students =
            storage.findAll(input.getParams(), input.getCriteria());
        return new ReadStudentsOutput(students);
    }
}
