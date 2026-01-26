package io.github.arivanamin.lms.backend.student.core.query;

import io.github.arivanamin.lms.backend.base.core.pagination.PaginatedResponse;
import io.github.arivanamin.lms.backend.base.core.pagination.PaginationCriteria;
import io.github.arivanamin.lms.backend.student.core.entity.Student;
import io.github.arivanamin.lms.backend.student.core.persistence.StudentStorage;
import io.github.arivanamin.lms.backend.testing.architecture.bases.BaseUnitTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@Slf4j
class ReadStudentsQueryTest implements BaseUnitTest {

    private final List<Student> students = List.of();
    private final PaginationCriteria paginationCriteria = PaginationCriteria.of(0, 3);

    @Mock
    private StudentStorage storage;

    @InjectMocks
    private ReadStudentsQuery query;

    @Test
    void queryShouldCallStorageFindAll () {
        givenQueryWithMockStorage();
        whenQueryIsExecuted();
        thenVerifyQueryCallsFindAll();
    }

    private void givenQueryWithMockStorage () {
        when(storage.findAll(paginationCriteria)).thenReturn(
            PaginatedResponse.of(PAGE_DATA, students));
    }

    private PaginatedResponse<Student> whenQueryIsExecuted () {
        return query.execute(paginationCriteria);
    }

    private void thenVerifyQueryCallsFindAll () {
        verify(storage, times(1)).findAll(paginationCriteria);
    }

    @Test
    void queryShouldReturnResultOfStorageFindAll () {
        givenQueryWithMockStorage();
        List<Student> result = whenQueryIsExecuted().content();
        thenVerifyFindAllResultIsReturned(result);
    }

    private void thenVerifyFindAllResultIsReturned (List<Student> result) {
        assertThat(students).isSameAs(result);
    }
}
