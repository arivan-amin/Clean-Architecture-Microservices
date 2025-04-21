package io.github.arivanamin.scm.backend.testing.architecture.bases;

import com.github.javafaker.Faker;
import io.github.arivanamin.scm.backend.base.domain.pagination.PageData;
import io.github.arivanamin.scm.backend.base.domain.pagination.PaginationCriteria;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@SuppressWarnings ("NewClassNamingConvention")
@ExtendWith (MockitoExtension.class)
public interface BaseUnitTest {

    Faker FAKER = new Faker();

    PaginationCriteria PAGINATION_CRITERIA = PaginationCriteria.of(0, 10);

    PageData PAGE_DATA = PageData.of(5, 15, 3, 0);
}
