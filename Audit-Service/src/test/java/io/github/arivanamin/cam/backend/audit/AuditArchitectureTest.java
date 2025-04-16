package io.github.arivanamin.cam.backend.audit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import io.github.arivanamin.cam.backend.testing.architecture.rules.CleanArchitectureRules;
import io.github.arivanamin.cam.backend.testing.architecture.rules.CommonBestPracticeRules;

import static io.github.arivanamin.cam.backend.base.domain.config.CoreApplicationConfig.BASE_PACKAGE;

@AnalyzeClasses (packages = BASE_PACKAGE, importOptions = ImportOption.DoNotIncludeTests.class)
class AuditArchitectureTest implements CommonBestPracticeRules, CleanArchitectureRules {

}
