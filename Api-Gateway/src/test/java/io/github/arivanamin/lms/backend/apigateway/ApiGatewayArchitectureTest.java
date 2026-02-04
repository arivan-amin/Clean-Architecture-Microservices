package io.github.arivanamin.lms.backend.apigateway;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import io.github.arivanamin.lms.backend.testing.architecture.rules.CleanArchitectureRules;
import io.github.arivanamin.lms.backend.testing.architecture.rules.CommonBestPracticeRules;

import static io.github.arivanamin.lms.backend.core.domain.config.CoreApplicationConfig.BASE_PACKAGE;

@AnalyzeClasses (packages = BASE_PACKAGE, importOptions = ImportOption.DoNotIncludeTests.class)
class ApiGatewayArchitectureTest implements CommonBestPracticeRules, CleanArchitectureRules {

}
