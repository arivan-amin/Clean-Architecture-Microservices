package io.github.arivanamin.lms.backend.student.application.openapi;

import io.github.arivanamin.lms.backend.core.application.config.OpenApiServerProperties;
import io.github.arivanamin.lms.backend.core.application.openapi.OpenApiDetails;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
class StudentOpenApiConfig {

    private final OpenApiServerProperties serverProperties;

    @Bean
    public OpenAPI myOpenAPI () {
        Server server = new Server();
        server.setUrl(serverProperties.url());
        server.setDescription("Server URL");

        Info info = new Info().title("Student Service API")
            .description("Provides all the API related to Student service")
            .version("1.0")
            .contact(OpenApiDetails.getOpenApiContactDetails())
            .termsOfService(OpenApiDetails.getOpenApiTermsOfService())
            .license(OpenApiDetails.getOpenApiLicence());

        return new OpenAPI().info(info)
            .servers(List.of(server));
    }
}
