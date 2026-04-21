package io.github.arivanamin.cinemayan.outbox;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import io.github.arivanamin.cinemayan.testing.architecture.rules.TestingBestPracticeRules;

import static io.github.arivanamin.cinemayan.core.domain.config.CoreApplicationConfig.BASE_PACKAGE;

@AnalyzeClasses (packages = BASE_PACKAGE, importOptions = ImportOption.OnlyIncludeTests.class)
class OutboxStorageTestingBestPracticeRulesTest implements TestingBestPracticeRules {

}
