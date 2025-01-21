package com.arivanamin.healthcare.backend.testing.architecture.bases;

import com.arivanamin.healthcare.backend.base.domain.pagination.PageData;
import com.arivanamin.healthcare.backend.base.domain.pagination.PaginationCriteria;
import com.github.javafaker.Faker;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@SuppressWarnings ("NewClassNamingConvention")
@ExtendWith (MockitoExtension.class)
public interface BaseUnitTest {
    
    Faker FAKER = new Faker();
    
    EasyRandom RANDOM = new EasyRandom();
    
    PaginationCriteria PAGINATION_CRITERIA = PaginationCriteria.of(0, 10);
    
    PageData PAGE_DATA = PageData.of(5, 15, 3, 0);
}
