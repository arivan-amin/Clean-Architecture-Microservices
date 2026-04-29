package com.cinemayan.audit;

import com.cinemayan.testing.architecture.rules.TestingBestPracticeRules;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;

import static com.cinemayan.core.domain.config.CoreApplicationConfig.BASE_PACKAGE;

@AnalyzeClasses (packages = BASE_PACKAGE, importOptions = ImportOption.OnlyIncludeTests.class)
class AuditTestingBestPracticeRulesTest implements TestingBestPracticeRules {

}
