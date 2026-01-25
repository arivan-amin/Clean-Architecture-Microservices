package io.github.arivanamin.lms.backend.student.core.query;

import io.github.arivanamin.lms.backend.base.core.pagination.PaginatedResponse;
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

    @Test
    void queryShouldReturnResultOfStorageFindAll () {
        givenQueryWithMockStorage();
        List<Student> result = whenQueryIsExecuted().content();
        thenVerifyFindAllResultIsReturned(result);
    }

    private void givenQueryWithMockStorage () {
        when(storage.findAll(PAGINATION_CRITERIA)).thenReturn(
            PaginatedResponse.of(PAGE_DATA, students));
    }

    private void thenVerifyQueryCallsFindAll () {
        verify(storage, times(1)).findAll(PAGINATION_CRITERIA);
    }

    private PaginatedResponse<Student> whenQueryIsExecuted () {
        return query.execute(PAGINATION_CRITERIA);
    }

    private void thenVerifyFindAllResultIsReturned (List<Student> result) {
        assertThat(students).isSameAs(result);
    }
}
