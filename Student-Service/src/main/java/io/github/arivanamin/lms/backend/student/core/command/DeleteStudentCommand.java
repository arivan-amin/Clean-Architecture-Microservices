package io.github.arivanamin.lms.backend.student.core.command;

import io.github.arivanamin.lms.backend.student.core.persistence.StudentStorage;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DeleteStudentCommand {

    private final StudentStorage storage;

    public void execute (UUID id) {
        storage.delete(id);
    }
}
