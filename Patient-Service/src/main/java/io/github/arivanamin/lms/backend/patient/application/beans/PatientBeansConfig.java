package io.github.arivanamin.lms.backend.patient.application.beans;

import io.github.arivanamin.lms.backend.patient.core.command.*;
import io.github.arivanamin.lms.backend.patient.core.persistence.PatientStorage;
import io.github.arivanamin.lms.backend.patient.core.query.ReadPatientByIdQuery;
import io.github.arivanamin.lms.backend.patient.core.query.ReadPatientsQuery;
import io.github.arivanamin.lms.backend.patient.storage.DatabasePatientStorage;
import io.github.arivanamin.lms.backend.patient.storage.repository.PatientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class PatientBeansConfig {

    @Bean
    public PatientStorage storage (PatientRepository repository) {
        return new DatabasePatientStorage(repository);
    }

    @Bean
    public ReadPatientsQuery readPatientsQuery (PatientStorage storage) {
        return new ReadPatientsQuery(storage);
    }

    @Bean
    public ReadPatientByIdQuery readPatientByIdQuery (PatientStorage storage) {
        return new ReadPatientByIdQuery(storage);
    }

    @Bean
    public CreatePatientCommand createPatientCommand (PatientStorage storage) {
        return new CreatePatientCommand(storage);
    }

    @Bean
    public UpdatePatientCommand updatePatientCommand (PatientStorage storage) {
        return new UpdatePatientCommand(storage);
    }

    @Bean
    public DeletePatientCommand deletePatientCommand (PatientStorage storage) {
        return new DeletePatientCommand(storage);
    }
}
