package io.github.arivanamin.scm.backend.audit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import io.github.arivanamin.scm.backend.testing.architecture.rules.TestingBestPracticeRules;

import static io.github.arivanamin.scm.backend.base.core.config.CoreApplicationConfig.BASE_PACKAGE;

@AnalyzeClasses (packages = BASE_PACKAGE, importOptions = ImportOption.OnlyIncludeTests.class)
class AuditTestingBestPracticeRulesTest implements TestingBestPracticeRules {

}
