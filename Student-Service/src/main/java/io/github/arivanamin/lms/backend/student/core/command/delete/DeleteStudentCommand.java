package io.github.arivanamin.lms.backend.student.core.command.delete;

import io.github.arivanamin.lms.backend.student.core.persistence.StudentStorage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteStudentCommand {

    private final StudentStorage storage;

    public void execute (DeleteStudentCommandInput input) {
        storage.delete(input.getId());
    }
}
