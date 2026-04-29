package com.cinemayan.discovery;

import com.cinemayan.testing.architecture.rules.CleanArchitectureRules;
import com.cinemayan.testing.architecture.rules.CommonBestPracticeRules;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;

import static com.cinemayan.core.domain.config.CoreApplicationConfig.BASE_PACKAGE;

@AnalyzeClasses (packages = BASE_PACKAGE, importOptions = ImportOption.DoNotIncludeTests.class)
class DiscoveryServerArchitectureTest implements CommonBestPracticeRules, CleanArchitectureRules {

}
