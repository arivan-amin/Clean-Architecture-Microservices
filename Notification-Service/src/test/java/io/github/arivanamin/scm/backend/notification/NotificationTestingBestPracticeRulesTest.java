package io.github.arivanamin.scm.backend.notification;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import io.github.arivanamin.scm.backend.testing.architecture.rules.TestingBestPracticeRules;

import static io.github.arivanamin.scm.backend.base.domain.config.CoreApplicationConfig.BASE_PACKAGE;

@AnalyzeClasses (packages = BASE_PACKAGE, importOptions = ImportOption.OnlyIncludeTests.class)
class NotificationTestingBestPracticeRulesTest implements TestingBestPracticeRules {

}
