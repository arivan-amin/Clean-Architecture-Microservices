package com.arivanamin.healthcare.backend.patient.application.config;

import com.arivanamin.healthcare.backend.base.domain.notification.NotificationPublisher;
import com.arivanamin.healthcare.backend.patient.core.command.*;
import com.arivanamin.healthcare.backend.patient.core.persistence.PatientStorage;
import com.arivanamin.healthcare.backend.patient.core.query.ReadPatientByIdQuery;
import com.arivanamin.healthcare.backend.patient.core.query.ReadPatientsQuery;
import com.arivanamin.healthcare.backend.patient.storage.JpaPatientStorage;
import com.arivanamin.healthcare.backend.patient.storage.PatientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
class CommandAndQueryBeansConfig {

    @Bean
    public PatientStorage storage (PatientRepository repository) {
        return new JpaPatientStorage(repository);
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
    public CreatePatientCommand createPatientCommand (PatientStorage storage,
                                                      NotificationPublisher notification) {
        return new CreatePatientCommand(storage, notification);
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
