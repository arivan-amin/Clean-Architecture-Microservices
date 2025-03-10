package com.arivanamin.healthcare.backend.patient.core.command;

import com.arivanamin.healthcare.backend.base.domain.notification.NotificationPublisher;
import com.arivanamin.healthcare.backend.base.domain.notification.NotificationRequest;
import com.arivanamin.healthcare.backend.patient.core.entity.Patient;
import com.arivanamin.healthcare.backend.patient.core.exception.PatientAlreadyExistsException;
import com.arivanamin.healthcare.backend.patient.core.persistence.PatientStorage;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.UUID;

import static com.arivanamin.healthcare.backend.base.domain.notification.NotificationChannel.EMAIL;
import static com.arivanamin.healthcare.backend.base.domain.topics.NotificationTopics.NOTIFICATION_TOPIC;

@RequiredArgsConstructor
public class CreatePatientCommand {

    private final PatientStorage storage;
    private final NotificationPublisher publisher;

    public UUID execute (Patient patient) {
        if (doesPatientExist(patient)) {
            throw new PatientAlreadyExistsException();
        }
        UUID createdPatientId = storage.create(patient);

        NotificationRequest notificationRequest = NotificationRequest.builder()
            .channel(EMAIL)
            .recipient(patient.getEmail())
            .templateName("PATIENT_WELCOME_EMAIL")
            .variables(Map.of("patientEmail", patient.getEmail()))
            .build();

        publisher.sendNotification(NOTIFICATION_TOPIC, notificationRequest);
        return createdPatientId;
    }

    private boolean doesPatientExist (Patient patient) {
        return storage.findByEmail(patient.getEmail())
            .isPresent();
    }
}
