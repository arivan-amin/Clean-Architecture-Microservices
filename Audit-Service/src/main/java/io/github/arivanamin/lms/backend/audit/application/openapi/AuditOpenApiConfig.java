package io.github.arivanamin.lms.backend.audit.application.openapi;

import io.github.arivanamin.lms.backend.core.application.config.OpenApiServerProperties;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static io.github.arivanamin.lms.backend.core.application.openapi.OpenApiDetails.*;

@Configuration
@RequiredArgsConstructor
class AuditOpenApiConfig {

    private final OpenApiServerProperties serverProperties;

    @Bean
    public OpenAPI myOpenAPI () {
        Server server = new Server();
        server.setUrl(serverProperties.url());
        server.setDescription("Server URL");

        Info info = new Info().title("Audit Service API")
            .description("Provides all the API related to Audit service")
            .version("1.0")
            .contact(getOpenApiContactDetails())
            .termsOfService(getOpenApiTermsOfService())
            .license(getOpenApiLicence());

        return new OpenAPI().info(info)
            .servers(List.of(server));
    }
}
