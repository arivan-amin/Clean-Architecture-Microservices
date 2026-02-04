package io.github.arivanamin.lms.backend.student.domain.command;

import io.github.arivanamin.lms.backend.student.StudentTestData;
import io.github.arivanamin.lms.backend.student.domain.command.update.UpdateStudentCommand;
import io.github.arivanamin.lms.backend.student.domain.command.update.UpdateStudentCommandInput;
import io.github.arivanamin.lms.backend.student.domain.entity.Student;
import io.github.arivanamin.lms.backend.student.domain.persistence.StudentStorage;
import io.github.arivanamin.lms.backend.testing.architecture.bases.BaseUnitTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class UpdateStudentCommandTest implements BaseUnitTest {

    private final Student student = StudentTestData.withDefaultEmail();

    @Mock
    private StudentStorage storage;

    @InjectMocks
    private UpdateStudentCommand command;

    @Test
    void commandShouldCallStorageUpdate () {
        givenCommandWithMockStorage();
        whenCommandIsExecuted();
        thenVerifyCommandCallsStorageUpdate();
    }

    private void givenCommandWithMockStorage () {
    }

    private void whenCommandIsExecuted () {
        command.execute(new UpdateStudentCommandInput(student));
    }

    private void thenVerifyCommandCallsStorageUpdate () {
        verify(storage, times(1)).update(any());
    }

    @Test
    void commandShouldPassParameterToStorage () {
        givenCommandWithMockStorage();
        whenCommandIsExecuted();
        thenAssertCommandPassesParameterToStorage();
    }

    private void thenAssertCommandPassesParameterToStorage () {
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(storage).update(studentArgumentCaptor.capture());
        Student result = studentArgumentCaptor.getValue();
        Assertions.assertThat(result)
            .isSameAs(student);
    }
}
