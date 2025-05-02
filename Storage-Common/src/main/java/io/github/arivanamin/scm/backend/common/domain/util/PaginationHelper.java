package io.github.arivanamin.scm.backend.common.domain.util;

import io.github.arivanamin.scm.backend.base.domain.pagination.PageData;
import io.github.arivanamin.scm.backend.base.domain.pagination.PaginatedResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public final class PaginationHelper {

    public static <T> PaginatedResponse<T> buildPaginatedResponse (List<T> elements, Page<?> page) {
        return PaginatedResponse.of(extractPageData(page), elements);
    }

    private static PageData extractPageData (Page<?> page) {
        return PageData.of(page.getNumber(), page.getTotalPages(), page.getSize(),
            page.getTotalElements());
    }
}
