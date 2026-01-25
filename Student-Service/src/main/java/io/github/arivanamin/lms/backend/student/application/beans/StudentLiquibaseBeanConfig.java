package io.github.arivanamin.lms.backend.student.application.beans;

import liquibase.integration.spring.SpringLiquibase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

import static io.github.arivanamin.lms.backend.base.core.config.CoreApplicationConfig.LIQUIBASE_CHANGELOG_PATH;

@Configuration
@Slf4j
class StudentLiquibaseBeanConfig {

    @Bean
    public SpringLiquibase liquibase (DataSource dataSource) {
        log.info("Initializing Student Liquibase Bean");
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(LIQUIBASE_CHANGELOG_PATH);
        liquibase.setShouldRun(true);
        return liquibase;
    }
}
