package io.github.arivanamin.lms.backend.student.core.query;

import io.github.arivanamin.lms.backend.student.StudentTestData;
import io.github.arivanamin.lms.backend.student.core.entity.Student;
import io.github.arivanamin.lms.backend.student.core.exception.StudentNotFoundException;
import io.github.arivanamin.lms.backend.student.core.persistence.StudentStorage;
import io.github.arivanamin.lms.backend.testing.architecture.bases.BaseUnitTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@Slf4j
class ReadStudentByIdQueryTest implements BaseUnitTest {

    private final UUID id = UUID.randomUUID();
    private final Student student = StudentTestData.withDefaultEmail();

    @Mock
    private StudentStorage storage;

    @InjectMocks
    private ReadStudentByIdQuery query;

    @Test
    void queryShouldCallStorageFindById () {
        givenQueryWithMockStorage();
        whenQueryIsExecuted();
        thenVerifyQueryCallsStorageFindById();
    }

    private void givenQueryWithMockStorage () {
        when(storage.findById(id)).thenReturn(Optional.of(student));
    }

    private Student whenQueryIsExecuted () {
        return query.execute(id);
    }

    private void thenVerifyQueryCallsStorageFindById () {
        verify(storage, times(1)).findById(id);
    }

    @Test
    void executeShouldReturnResultFromStorageFindById () {
        givenQueryWithMockStorage();
        Student result = whenQueryIsExecuted();
        thenVerifyFindByIdResultIsReturned(result);
    }

    private void thenVerifyFindByIdResultIsReturned (Student result) {
        assertThat(result).isSameAs(student);
    }

    @Test
    void queryShouldThrowExceptionWhenStudentNotFound () {
        givenQueryWithMockStorageThatThrowsException();
        thenAssertQueryThrowsStudentNotFoundException();
    }

    private void givenQueryWithMockStorageThatThrowsException () {
        when(storage.findById(id)).thenThrow(StudentNotFoundException.class);
    }

    private void thenAssertQueryThrowsStudentNotFoundException () {
        assertThrows(StudentNotFoundException.class, () -> query.execute(id));
    }
}
