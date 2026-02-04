package io.github.arivanamin.lms.backend.audit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import static io.github.arivanamin.lms.backend.core.domain.config.CoreApplicationConfig.BASE_PACKAGE;

@SpringBootApplication (scanBasePackages = BASE_PACKAGE)
@EnableJpaRepositories (basePackages = BASE_PACKAGE)
@EntityScan (basePackages = BASE_PACKAGE)
@EnableDiscoveryClient
@EnableCaching
@EnableScheduling
public class AuditApplication {

    static void main (String[] args) {
        SpringApplication.run(AuditApplication.class, args);
    }
}
