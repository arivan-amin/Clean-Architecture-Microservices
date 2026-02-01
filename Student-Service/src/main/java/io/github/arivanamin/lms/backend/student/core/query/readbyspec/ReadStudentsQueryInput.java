package io.github.arivanamin.lms.backend.student.core.query.readbyspec;

import io.github.arivanamin.lms.backend.base.core.pagination.PaginationCriteria;
import lombok.Value;

@Value
public class ReadStudentsQueryInput {

    PaginationCriteria criteria;
}
