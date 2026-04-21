package io.github.arivanamin.cinemayan.catalog.domain.query.readbyspec;

import io.github.arivanamin.cinemayan.catalog.domain.persistence.ReadStudentsParams;
import io.github.arivanamin.cinemayan.core.domain.pagination.PaginationCriteria;
import lombok.Value;

@Value
public class ReadStudentsInput {

    ReadStudentsParams params;
    PaginationCriteria criteria;
}
