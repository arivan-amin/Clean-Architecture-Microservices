package io.github.arivanamin.lms.backend.student.domain.query.readbyspec;

import io.github.arivanamin.lms.backend.core.domain.pagination.PaginationCriteria;
import lombok.Value;

@Value
public class ReadStudentsQueryInput {

    PaginationCriteria criteria;
}
