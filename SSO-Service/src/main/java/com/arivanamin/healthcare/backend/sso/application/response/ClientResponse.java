package com.arivanamin.healthcare.backend.sso.application.response;

import com.arivanamin.healthcare.backend.sso.core.entity.Client;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse {

    UUID id;
    String secret;

    public static ClientResponse of (Client client) {
        return new ModelMapper().map(client, ClientResponse.class);
    }
}
