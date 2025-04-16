package io.github.arivanamin.cam.backend.patient.core.command;

import io.github.arivanamin.cam.backend.base.domain.notification.NotificationPublisher;
import io.github.arivanamin.cam.backend.base.domain.notification.NotificationRequest;
import io.github.arivanamin.cam.backend.patient.core.entity.Patient;
import io.github.arivanamin.cam.backend.patient.core.exception.PatientAlreadyExistsException;
import io.github.arivanamin.cam.backend.patient.core.persistence.PatientStorage;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import static io.github.arivanamin.cam.backend.base.domain.notification.NotificationChannel.EMAIL;
import static io.github.arivanamin.cam.backend.base.domain.topics.NotificationTopics.NOTIFICATION_TOPIC;

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
            .content("Welcome")
            .referenceId(patient.getEmail())
            .build();

        publisher.sendNotification(NOTIFICATION_TOPIC, notificationRequest);
        return createdPatientId;
    }

    private boolean doesPatientExist (Patient patient) {
        return storage.findByEmail(patient.getEmail())
            .isPresent();
    }
}
