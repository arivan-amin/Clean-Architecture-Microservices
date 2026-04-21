package io.github.arivanamin.cinemayan.student.domain.command.delete;

import io.github.arivanamin.cinemayan.student.domain.persistence.StudentStorage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteStudentCommand {

    private final StudentStorage storage;

    public void execute (DeleteStudentInput input) {
        storage.delete(input.getId());
    }
}
