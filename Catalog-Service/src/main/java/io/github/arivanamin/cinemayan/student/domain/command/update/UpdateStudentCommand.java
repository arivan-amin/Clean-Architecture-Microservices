package io.github.arivanamin.cinemayan.student.domain.command.update;

import io.github.arivanamin.cinemayan.student.domain.persistence.StudentStorage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateStudentCommand {

    private final StudentStorage storage;

    public void execute (UpdateStudentInput input) {
        storage.update(input.getStudent());
    }
}
