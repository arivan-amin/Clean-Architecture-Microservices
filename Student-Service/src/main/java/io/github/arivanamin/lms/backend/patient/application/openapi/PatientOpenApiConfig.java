package io.github.arivanamin.lms.backend.patient.application.openapi;

import io.github.arivanamin.lms.backend.base.application.config.OpenApiServerProperties;
import io.github.arivanamin.lms.backend.base.application.openapi.OpenApiDetails;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
class PatientOpenApiConfig {

    private final OpenApiServerProperties serverProperties;

    @Bean
    public OpenAPI myOpenAPI () {
        Server server = new Server();
        server.setUrl(serverProperties.url());
        server.setDescription("Server URL");

        Info info = new Info().title("Patient Service API")
            .description("Provides all the API related to Patient service")
            .version("1.0")
            .contact(OpenApiDetails.getOpenApiContactDetails())
            .termsOfService(OpenApiDetails.getOpenApiTermsOfService())
            .license(OpenApiDetails.getOpenApiLicence());

        return new OpenAPI().info(info)
            .servers(List.of(server));
    }
}
