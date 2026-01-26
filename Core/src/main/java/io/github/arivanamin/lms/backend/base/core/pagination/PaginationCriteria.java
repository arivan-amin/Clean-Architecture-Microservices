package io.github.arivanamin.lms.backend.base.core.pagination;

import jakarta.validation.constraints.*;
import lombok.Value;

@Value
public class PaginationCriteria {

    // todo 1/26/26 - max size pe page should be configurable
    public static final int MAX_SIZE = 50;

    public static PaginationCriteria of (int page, int size) {
        return new PaginationCriteria(page, size);
    }

    @NotNull
    @PositiveOrZero
    int page;

    @NotNull
    @Positive
    @Max (MAX_SIZE)
    int size;
}
