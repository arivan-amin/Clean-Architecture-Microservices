package com.arivanamin.healthcare.backend.sso.application.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.arivanamin.healthcare.backend.base.application.openapi.OpenApiDetails.*;

@Configuration
class SsoOpenApiConfig {

    @Bean
    public OpenAPI myOpenAPI () {
        Server server = new Server();
        server.setUrl(APPLICATION_SERVER_URL);
        server.setDescription("Server URL");

        Info info = new Info().title("SSO Service API")
            .description("Provides all the API related to SSO service")
            .version("1.0")
            .contact(getOpenApiContactDetails())
            .termsOfService(getOpenApiTermsOfService())
            .license(getOpenApiLicence());

        return new OpenAPI().info(info)
            .servers(List.of(server));
    }
}
