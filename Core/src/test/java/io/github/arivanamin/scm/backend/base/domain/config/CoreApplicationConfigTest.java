package io.github.arivanamin.scm.backend.base.domain.config;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static io.github.arivanamin.scm.backend.base.domain.config.CoreApplicationConfig.USER_HOME_DIRECTORY_PROPERTY;
import static java.io.File.separator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.ReflectionTestUtils.invokeMethod;

class CoreApplicationConfigTest {

    @Test
    void getApplicationConfigDirectoryShouldReturnCorrectPath () {
        String userHomeDirectory = System.getProperty(USER_HOME_DIRECTORY_PROPERTY);
        String expectedPath =
            userHomeDirectory + separator + "Apps" + separator + "Spring-Clean-Microservices";

        Path actualPath = CoreApplicationConfig.getApplicationConfigDirectory();

        assertEquals(expectedPath, actualPath.toString());
    }

    @Test
    void getUserHomeDirectoryShouldReturnPathAsString () {
        String expectedUserHomeDirectory = System.getProperty(USER_HOME_DIRECTORY_PROPERTY);

        String actualUserHomeDirectory =
            invokeMethod(CoreApplicationConfig.class, "getUserHomeDirectory");

        assertEquals(expectedUserHomeDirectory, actualUserHomeDirectory);
    }
}
