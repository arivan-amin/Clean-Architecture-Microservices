package io.github.arivanamin.cam.backend.audit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import io.github.arivanamin.cam.backend.testing.architecture.rules.TestingBestPracticeRules;

import static io.github.arivanamin.cam.backend.base.domain.config.CoreApplicationConfig.BASE_PACKAGE;

@AnalyzeClasses (packages = BASE_PACKAGE, importOptions = ImportOption.OnlyIncludeTests.class)
class AuditTestingBestPracticeRulesTest implements TestingBestPracticeRules {

}
