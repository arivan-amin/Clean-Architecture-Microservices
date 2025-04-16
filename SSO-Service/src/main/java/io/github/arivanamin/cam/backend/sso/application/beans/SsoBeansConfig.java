package io.github.arivanamin.cam.backend.sso.application.beans;

import io.github.arivanamin.cam.backend.sso.application.secret.SecureRandomSecretGenerator;
import io.github.arivanamin.cam.backend.sso.core.command.CreateSsoClientCommand;
import io.github.arivanamin.cam.backend.sso.core.persistence.SsoStorage;
import io.github.arivanamin.cam.backend.sso.core.query.ReadClientByIdQuery;
import io.github.arivanamin.cam.backend.sso.core.secret.ClientSecretGenerator;
import io.github.arivanamin.cam.backend.sso.storage.KeycloakSsoStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
class SsoBeansConfig {

    @Bean
    public ClientSecretGenerator secretGenerator () {
        return SecureRandomSecretGenerator.newInstance();
    }

    @Bean
    public SsoStorage storage (ClientSecretGenerator secretGenerator) {
        return new KeycloakSsoStorage(secretGenerator);
    }

    @Bean
    public ReadClientByIdQuery readClientByIdQuery (SsoStorage storage) {
        return new ReadClientByIdQuery(storage);
    }

    @Bean
    public CreateSsoClientCommand createClientCommand (SsoStorage storage) {
        return new CreateSsoClientCommand(storage);
    }
}
