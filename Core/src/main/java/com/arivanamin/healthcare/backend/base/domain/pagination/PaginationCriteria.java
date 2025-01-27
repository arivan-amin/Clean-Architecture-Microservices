package com.arivanamin.healthcare.backend.base.domain.pagination;

import lombok.Value;

@Value
public class PaginationCriteria {

    public static final int MAX_PAGE_SIZE = 100;
    int page;
    int size;

    public static PaginationCriteria of (int page, int size) {
        throwExceptionIfPageSizeExceedsMaximumLimit(size);
        return new PaginationCriteria(page, size);
    }

    private static void throwExceptionIfPageSizeExceedsMaximumLimit (int size) {
        if (pageSizeExceedsMaximumLimit(size)) {
            throw new IllegalArgumentException("Page size can't be more than " + MAX_PAGE_SIZE);
        }
    }

    private static boolean pageSizeExceedsMaximumLimit (int size) {
        return size > MAX_PAGE_SIZE;
    }
}
