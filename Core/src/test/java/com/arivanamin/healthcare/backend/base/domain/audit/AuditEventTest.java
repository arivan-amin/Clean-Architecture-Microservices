package com.arivanamin.healthcare.backend.base.domain.audit;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;

@Slf4j
class AuditEventTest {

    EasyRandom random = new EasyRandom();

    @Test
    void should () throws Exception {
        AuditEvent event = AuditEvent.builder()
            .serviceName("patient-service")
            .action("Update")
            .location("/patients/protected/v1/accounts/{id}")
            // .data("00b847e8-d9ac-4ec4-a798-e0f97b37f176, UpdatePatientRequest
            // (firstName=Houston, " +
            //     "lastName=Okuneva, email=Garrick_Schuster@hotmail.com, " +
            //     "dateOfBirth=2024-12-23, gender=MALE, address=Johnston Vista)")
            .timestamp(1734935566)
            .build();
        log.info("event = {}", event);
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(event);
        log.info("result = {}", result);
    }
}
