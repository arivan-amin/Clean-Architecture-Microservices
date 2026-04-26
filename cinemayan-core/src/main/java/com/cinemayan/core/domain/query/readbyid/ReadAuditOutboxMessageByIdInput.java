package com.cinemayan.core.domain.query.readbyid;

import lombok.Value;

import java.util.UUID;

@Value
public class ReadAuditOutboxMessageByIdInput {

    UUID id;
}
