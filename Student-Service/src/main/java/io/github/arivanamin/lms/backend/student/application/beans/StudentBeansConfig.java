package io.github.arivanamin.lms.backend.student.application.beans;

import io.github.arivanamin.lms.backend.student.core.command.create.CreateStudentCommand;
import io.github.arivanamin.lms.backend.student.core.command.delete.DeleteStudentCommand;
import io.github.arivanamin.lms.backend.student.core.command.update.UpdateStudentCommand;
import io.github.arivanamin.lms.backend.student.core.persistence.StudentStorage;
import io.github.arivanamin.lms.backend.student.core.query.readbyid.ReadStudentByIdQuery;
import io.github.arivanamin.lms.backend.student.core.query.readbyspec.ReadStudentsQuery;
import io.github.arivanamin.lms.backend.student.storage.DatabaseStudentStorage;
import io.github.arivanamin.lms.backend.student.storage.repository.StudentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class StudentBeansConfig {

    @Bean
    public StudentStorage storage (StudentRepository repository) {
        return new DatabaseStudentStorage(repository);
    }

    @Bean
    public ReadStudentsQuery readStudentsQuery (StudentStorage storage) {
        return new ReadStudentsQuery(storage);
    }

    @Bean
    public ReadStudentByIdQuery readStudentByIdQuery (StudentStorage storage) {
        return new ReadStudentByIdQuery(storage);
    }

    @Bean
    public CreateStudentCommand createStudentCommand (StudentStorage storage) {
        return new CreateStudentCommand(storage);
    }

    @Bean
    public UpdateStudentCommand updateStudentCommand (StudentStorage storage) {
        return new UpdateStudentCommand(storage);
    }

    @Bean
    public DeleteStudentCommand deleteStudentCommand (StudentStorage storage) {
        return new DeleteStudentCommand(storage);
    }
}
