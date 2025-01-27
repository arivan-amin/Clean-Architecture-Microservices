package com.arivanamin.healthcare.backend.patient;

import com.arivanamin.healthcare.backend.testing.architecture.rules.CleanArchitectureRules;
import com.arivanamin.healthcare.backend.testing.architecture.rules.CommonBestPracticeRules;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;

import static com.arivanamin.healthcare.backend.base.domain.config.CoreApplicationConfig.BASE_PACKAGE;

@AnalyzeClasses (packages = BASE_PACKAGE, importOptions = ImportOption.DoNotIncludeTests.class)
class PatientArchitectureTest implements CommonBestPracticeRules, CleanArchitectureRules {

}
