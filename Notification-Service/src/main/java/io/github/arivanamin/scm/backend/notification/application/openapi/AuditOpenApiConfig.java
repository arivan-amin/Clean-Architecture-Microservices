package io.github.arivanamin.scm.backend.notification.application.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static io.github.arivanamin.scm.backend.base.application.openapi.OpenApiDetails.*;

@Configuration
class AuditOpenApiConfig {

    @Bean
    public OpenAPI myOpenAPI () {
        Server server = new Server();
        server.setUrl(APPLICATION_SERVER_URL);
        server.setDescription("Server URL");

        Info info = new Info().title("Notification Service API")
            .description("Provides all the API related to Notification service")
            .version("1.0")
            .contact(getOpenApiContactDetails())
            .termsOfService(getOpenApiTermsOfService())
            .license(getOpenApiLicence());

        return new OpenAPI().info(info)
            .servers(List.of(server));
    }
}
