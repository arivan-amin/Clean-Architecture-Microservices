package io.github.arivanamin.cam.backend.testing.architecture.bases;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
public interface BaseMongoDatabaseTest extends BaseIntegrationTest {

    @Container
    MongoDBContainer MONGO_DB_CONTAINER = new MongoDBContainer("mongo:8.0.0");

    @DynamicPropertySource
    static void registerProperties (DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", MONGO_DB_CONTAINER::getReplicaSetUrl);
    }
}
