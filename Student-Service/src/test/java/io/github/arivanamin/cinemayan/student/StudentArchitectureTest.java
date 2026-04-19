package io.github.arivanamin.cinemayan.student;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import io.github.arivanamin.lms.backend.testing.architecture.rules.CleanArchitectureRules;
import io.github.arivanamin.lms.backend.testing.architecture.rules.CommonBestPracticeRules;

import static io.github.arivanamin.cinemayan.backend.core.domain.config.CoreApplicationConfig.BASE_PACKAGE;

@AnalyzeClasses (packages = BASE_PACKAGE, importOptions = ImportOption.DoNotIncludeTests.class)
class StudentArchitectureTest implements CommonBestPracticeRules, CleanArchitectureRules {

}
