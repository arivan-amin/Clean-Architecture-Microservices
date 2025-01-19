package com.arivanamin.healthcare.backend.base.domain.pagination;

import lombok.Value;

@Value
public class PageData {
    
    int currentPage;
    int totalPages;
    int sizeOfEachPage;
    long totalElements;
    
    public static PageData of (int currentPage, int totalPages, int sizeOfEachPage,
                               long totalElements) {
        return new PageData(currentPage, totalPages, sizeOfEachPage, totalElements);
    }
}
