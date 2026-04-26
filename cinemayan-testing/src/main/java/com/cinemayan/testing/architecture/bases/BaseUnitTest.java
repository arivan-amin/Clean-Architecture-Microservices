package com.cinemayan.testing.architecture.bases;

import com.cinemayan.core.domain.pagination.PageData;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith (MockitoExtension.class)
public interface BaseUnitTest {

    PageData PAGE_DATA = PageData.of(0, 5, 5, 25);
}
