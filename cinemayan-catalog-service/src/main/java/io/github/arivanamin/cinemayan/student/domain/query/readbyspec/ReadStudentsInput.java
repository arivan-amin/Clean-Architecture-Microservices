package io.github.arivanamin.cinemayan.student.domain.query.readbyspec;

import io.github.arivanamin.cinemayan.core.domain.pagination.PaginationCriteria;
import io.github.arivanamin.cinemayan.student.domain.persistence.ReadStudentsParams;
import lombok.Value;

@Value
public class ReadStudentsInput {

    ReadStudentsParams params;
    PaginationCriteria criteria;
}
