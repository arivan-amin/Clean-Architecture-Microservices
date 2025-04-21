package io.github.arivanamin.scm.backend.sso.core.entity;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    UUID id;
    String secret;
}
