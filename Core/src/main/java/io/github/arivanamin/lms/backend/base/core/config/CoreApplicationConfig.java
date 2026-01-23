package io.github.arivanamin.lms.backend.base.core.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.nio.file.Path;
import java.time.ZoneId;

import static java.lang.System.getProperty;
import static java.nio.file.Paths.get;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public enum CoreApplicationConfig {
    ;

    public static final String APPLICATION_DIRECTORY_NAME = "Spring-Clean-Microservices";

    public static final String BASE_PACKAGE = "io.github.arivanamin.lms.backend";

    public static final String LIQUIBASE_CHANGELOG_PATH =
        "classpath:db/changelog/changelog-master.xml";

    public static final ZoneId DEFAULT_ZONE_ID = ZoneId.of("Asia/Baghdad");

    static final String USER_HOME_DIRECTORY_PROPERTY = "user.home";

    public static Path getApplicationConfigDirectory () {
        return get(getUserHomeDirectory(), "Apps", APPLICATION_DIRECTORY_NAME);
    }

    private static String getUserHomeDirectory () {
        return getProperty(USER_HOME_DIRECTORY_PROPERTY);
    }
}
