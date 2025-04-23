package io.github.arivanamin.scm.backend.patient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import static io.github.arivanamin.scm.backend.base.domain.config.CoreApplicationConfig.BASE_PACKAGE;

@SpringBootApplication (scanBasePackages = BASE_PACKAGE)
@EnableJpaRepositories (basePackages = BASE_PACKAGE)
@EntityScan (basePackages = BASE_PACKAGE)
@EnableDiscoveryClient
@EnableCaching
@EnableJpaAuditing
public class PatientApplication {

    public static void main (String[] args) {
        SpringApplication.run(PatientApplication.class, args);
    }
}
