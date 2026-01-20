package io.github.arivanamin.scm.backend.base.core.pagination;

import lombok.Value;

@Value
public class PaginationCriteria {

    public static final int MAX_SIZE = 100;
    int page;
    int size;

    public static PaginationCriteria of (int page, int size) {
        validatePage(page);
        validateSize(size);
        return new PaginationCriteria(page, size);
    }

    private static void validatePage (int page) {
        if (page < 0) {
            throw new IllegalArgumentException("Page must be zero or larger");
        }
    }

    private static void validateSize (int size) {
        validateSizeRange(size);
        validateSizeLimit(size);
    }

    private static void validateSizeRange (int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be larger than zero");
        }
    }

    private static void validateSizeLimit (int size) {
        if (size > MAX_SIZE) {
            throw new IllegalArgumentException("Page size can't be more than " + MAX_SIZE);
        }
    }
}
