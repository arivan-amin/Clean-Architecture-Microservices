package io.github.arivanamin.cam.backend.patient;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import io.github.arivanamin.cam.backend.testing.architecture.rules.TestingBestPracticeRules;

import static io.github.arivanamin.cam.backend.base.domain.config.CoreApplicationConfig.BASE_PACKAGE;

@AnalyzeClasses (packages = BASE_PACKAGE, importOptions = ImportOption.OnlyIncludeTests.class)
class PatientTestingBestPracticeRulesTest implements TestingBestPracticeRules {

}
