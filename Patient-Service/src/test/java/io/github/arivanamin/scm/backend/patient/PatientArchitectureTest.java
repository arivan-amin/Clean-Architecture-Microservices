package io.github.arivanamin.scm.backend.patient;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import io.github.arivanamin.scm.backend.testing.architecture.rules.CleanArchitectureRules;
import io.github.arivanamin.scm.backend.testing.architecture.rules.CommonBestPracticeRules;

import static io.github.arivanamin.scm.backend.base.domain.config.CoreApplicationConfig.BASE_PACKAGE;

@AnalyzeClasses (packages = BASE_PACKAGE, importOptions = ImportOption.DoNotIncludeTests.class)
class PatientArchitectureTest implements CommonBestPracticeRules, CleanArchitectureRules {

}
