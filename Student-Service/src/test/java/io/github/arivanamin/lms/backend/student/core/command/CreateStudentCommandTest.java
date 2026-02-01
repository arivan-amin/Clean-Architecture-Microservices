package io.github.arivanamin.lms.backend.student.core.command;

import io.github.arivanamin.lms.backend.student.StudentTestData;
import io.github.arivanamin.lms.backend.student.core.command.create.CreateStudentCommand;
import io.github.arivanamin.lms.backend.student.core.command.create.CreateStudentCommandInput;
import io.github.arivanamin.lms.backend.student.core.entity.Student;
import io.github.arivanamin.lms.backend.student.core.exception.StudentAlreadyExistsException;
import io.github.arivanamin.lms.backend.student.core.persistence.StudentStorage;
import io.github.arivanamin.lms.backend.testing.architecture.bases.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;
import static org.mockito.Mockito.*;

class CreateStudentCommandTest implements BaseUnitTest {

    private final UUID createdStudentId = UUID.randomUUID();
    private final String emailAddress = "christopher.nolan@example.com";

    @Mock
    private StudentStorage storage;

    @InjectMocks
    private CreateStudentCommand command;

    private Student student;

    @Test
    void commandShouldThrowExceptionWhenStudentExists () {
        givenCommandWithMockFindByEmail();
        whenEmailIsDuplicate();
        thenThrowStudentAlreadyExistsException();
    }

    private void givenCommandWithMockFindByEmail () {
        Student student = StudentTestData.withDefaultEmail();
        student.setEmail(emailAddress);
        when(storage.findByEmail(emailAddress)).thenReturn(Optional.of(student));
    }

    private void whenEmailIsDuplicate () {
        student = StudentTestData.withDefaultEmail();
        student.setEmail(emailAddress);
    }

    private void thenThrowStudentAlreadyExistsException () {
        assertThatException().isThrownBy(
                () -> command.execute(new CreateStudentCommandInput(student)))
            .isInstanceOf(StudentAlreadyExistsException.class);
    }

    @Test
    void commandShouldCallStorageCreate () {
        givenCommandWithMockStorage();
        whenCommandIsExecuted();
        thenVerifyCommandCallsCreate();
    }

    private void givenCommandWithMockStorage () {
        when(storage.create(any())).thenReturn(createdStudentId);
        student = StudentTestData.withDefaultEmail();
    }

    private UUID whenCommandIsExecuted () {
        return command.execute(new CreateStudentCommandInput(student))
            .getId();
    }

    private void thenVerifyCommandCallsCreate () {
        verify(storage, times(1)).create(student);
    }

    @Test
    void commandShouldReturnResultOfStorageCreate () {
        givenCommandWithMockStorage();
        UUID resultId = whenCommandIsExecuted();
        thenVerifyCreateResultIsReturned(resultId);
    }

    private void thenVerifyCreateResultIsReturned (UUID resultId) {
        assertThat(resultId).isSameAs(createdStudentId);
    }
}
