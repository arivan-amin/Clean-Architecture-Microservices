package io.github.arivanamin.lms.backend.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import static io.github.arivanamin.lms.backend.core.domain.config.CoreApplicationConfig.BASE_PACKAGE;

@SpringBootApplication (scanBasePackages = BASE_PACKAGE)
@EnableDiscoveryClient
public class ApiGatewayApplication {

    static void main (String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
