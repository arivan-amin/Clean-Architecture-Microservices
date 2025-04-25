package io.github.arivanamin.scm.backend.sso.core.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults (level = AccessLevel.PRIVATE)
public class Client {

    UUID id;
    String secret;
}
