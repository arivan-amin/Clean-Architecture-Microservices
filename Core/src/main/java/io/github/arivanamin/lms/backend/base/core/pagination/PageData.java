package io.github.arivanamin.lms.backend.base.core.pagination;

public record PageData(int currentPage, int totalPages, int pageSize, long totalElements) {

    public static PageData of (int currentPage, int totalPages, int pageSize, long totalElements) {
        return new PageData(currentPage, totalPages, pageSize, totalElements);
    }
}
