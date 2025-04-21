package io.github.arivanamin.scm.backend.testing.architecture.bases;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
public interface BaseDatabaseTest extends BaseIntegrationTest {

    @Container
    MySQLContainer<?> MYSQL_CONTAINER =
        new MySQLContainer<>("mysql:9.1.0").withDatabaseName("service_database")
            .withUsername("root")
            .withPassword("mysql");

    @DynamicPropertySource
    static void registerProperties (DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", MYSQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", MYSQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", MYSQL_CONTAINER::getPassword);
        registry.add("spring.datasource.driver-class-name", MYSQL_CONTAINER::getDriverClassName);
    }
}
