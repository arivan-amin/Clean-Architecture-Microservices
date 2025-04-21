package io.github.arivanamin.scm.backend.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import static io.github.arivanamin.scm.backend.base.domain.config.CoreApplicationConfig.BASE_PACKAGE;

@SpringBootApplication (scanBasePackages = BASE_PACKAGE)
@EnableDiscoveryClient
public class ApiGatewayApplication {

    public static void main (String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
