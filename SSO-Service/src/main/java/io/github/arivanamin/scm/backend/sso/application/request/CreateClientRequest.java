package io.github.arivanamin.scm.backend.sso.application.request;

import io.github.arivanamin.scm.backend.sso.core.entity.Client;
import lombok.*;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateClientRequest {

    String firstName;
    String lastName;
    String email;

    public Client toEntity () {
        return new ModelMapper().map(this, Client.class);
    }
}
