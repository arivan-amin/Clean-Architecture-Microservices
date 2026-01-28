package io.github.arivanamin.lms.backend.student.core.command.update;

import io.github.arivanamin.lms.backend.student.core.persistence.StudentStorage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateStudentCommand {

    private final StudentStorage storage;

    public void execute (UpdateStudentCommandInput input) {
        storage.update(input.getStudent());
    }
}
