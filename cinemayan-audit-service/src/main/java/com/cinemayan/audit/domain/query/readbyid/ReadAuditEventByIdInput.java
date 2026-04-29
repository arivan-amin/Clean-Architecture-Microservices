package com.cinemayan.audit.domain.query.readbyid;

import lombok.Value;

import java.util.UUID;

@Value
public class ReadAuditEventByIdInput {

    UUID id;
}
