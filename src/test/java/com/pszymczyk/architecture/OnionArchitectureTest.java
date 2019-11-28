package com.pszymczyk.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.onionArchitecture;

@AnalyzeClasses(packages = "com.pszymczyk")
class OnionArchitectureTest {

    @ArchTest
    static final ArchRule onion_architecture_is_respected = onionArchitecture()
            .domainModels("..domain.model..")
            .applicationServices("..application..")
            .adapter("app", "..adapter.app..")
            .adapter("persistence", "..adapter.persistence..")
            .adapter("rest", "..adapter.rest..");
}
