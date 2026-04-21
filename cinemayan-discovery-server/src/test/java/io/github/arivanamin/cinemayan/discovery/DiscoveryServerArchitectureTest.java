package io.github.arivanamin.cinemayan.discovery;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import io.github.arivanamin.cinemayan.testing.architecture.rules.CleanArchitectureRules;
import io.github.arivanamin.cinemayan.testing.architecture.rules.CommonBestPracticeRules;

import static io.github.arivanamin.cinemayan.core.domain.config.CoreApplicationConfig.BASE_PACKAGE;

@AnalyzeClasses (packages = BASE_PACKAGE, importOptions = ImportOption.DoNotIncludeTests.class)
class DiscoveryServerArchitectureTest implements CommonBestPracticeRules, CleanArchitectureRules {

}
