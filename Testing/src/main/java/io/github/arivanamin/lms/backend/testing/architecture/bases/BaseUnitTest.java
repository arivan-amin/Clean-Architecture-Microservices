package io.github.arivanamin.lms.backend.testing.architecture.bases;

import com.github.javafaker.Faker;
import io.github.arivanamin.lms.backend.base.core.pagination.PageData;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith (MockitoExtension.class)
public interface BaseUnitTest {

    Faker FAKER = new Faker();

    PageData PAGE_DATA = PageData.of(0, 5, 5, 25);
}
