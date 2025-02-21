package com.arivanamin.healthcare.backend.sso.application.config;

import com.arivanamin.healthcare.backend.sso.application.secret.SecureRandomSecretGenerator;
import com.arivanamin.healthcare.backend.sso.core.command.CreateSsoClientCommand;
import com.arivanamin.healthcare.backend.sso.core.persistence.SsoStorage;
import com.arivanamin.healthcare.backend.sso.core.query.ReadClientByIdQuery;
import com.arivanamin.healthcare.backend.sso.core.secret.ClientSecretGenerator;
import com.arivanamin.healthcare.backend.sso.storage.KeycloakSsoStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
class CommandAndQueryBeansConfig {

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
