package io.github.arivanamin.lms.backend.testing.architecture.bases;

import com.github.javafaker.Faker;
import io.github.arivanamin.lms.backend.base.core.pagination.PageData;
import io.github.arivanamin.lms.backend.base.core.pagination.PaginationCriteria;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import static org.springframework.data.domain.PageRequest.of;

@SuppressWarnings ("NewClassNamingConvention")
@ExtendWith (MockitoExtension.class)
public interface BaseUnitTest {

    Faker FAKER = new Faker();

    PaginationCriteria PAGINATION_CRITERIA = PaginationCriteria.of(0, 5);

    PageRequest PAGE_REQUEST = of(PAGINATION_CRITERIA.page(), PAGINATION_CRITERIA.size());

    PageData PAGE_DATA = PageData.of(0, 5, 5, 25);
}
