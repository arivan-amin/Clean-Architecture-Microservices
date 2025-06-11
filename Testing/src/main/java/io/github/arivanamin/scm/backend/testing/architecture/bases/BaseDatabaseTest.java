package io.github.arivanamin.scm.backend.testing.architecture.bases;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;

@SuppressWarnings ("resource")
@Transactional
public abstract class BaseDatabaseTest extends BaseIntegrationTest {

    @Container
    static final MySQLContainer<?> MYSQL_CONTAINER =
        new MySQLContainer<>("mysql:9.2.0").withDatabaseName("service_database")
            .withUsername("root")
            .withPassword("mysql");

    static {
        MYSQL_CONTAINER.start();
    }

    @DynamicPropertySource
    static void registerProperties (DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", MYSQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", MYSQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", MYSQL_CONTAINER::getPassword);
        registry.add("spring.datasource.driver-class-name", MYSQL_CONTAINER::getDriverClassName);
        registry.add("spring.jpa.properties.hibernate.connection.provider_disables_autocommit",
            () -> "false");
    }
}
