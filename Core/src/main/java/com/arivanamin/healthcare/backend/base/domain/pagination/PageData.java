package com.arivanamin.healthcare.backend.base.domain.pagination;

import lombok.Value;

@Value
public class PageData {
    
    int currentPage;
    int totalPages;
    int pageSize;
    long totalElements;
    
    public static PageData of (int currentPage, int totalPages, int pageSize, long totalElements) {
        return new PageData(currentPage, totalPages, pageSize, totalElements);
    }
}
