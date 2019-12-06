package com.pszymczyk.architecture;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaAnnotation;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.onionArchitecture;

@AnalyzeClasses(packages = "com.pszymczyk")
class ArchitectureTest {

    private static final DescribedPredicate<JavaAnnotation> JAVAX_PERSISTENCE_ANNOTATIONS = new DescribedPredicate<JavaAnnotation>("annotations from javax.persistence") {
        @Override
        public boolean apply(JavaAnnotation input) {
            return input.getRawType().getPackage().getName().startsWith("javax.persistence");
        }
    };

    private static final DescribedPredicate<JavaAnnotation> SPRING_ANNOTATIONS = new DescribedPredicate<JavaAnnotation>("annotations from org.springframework") {
        @Override
        public boolean apply(JavaAnnotation input) {
            return input.getRawType().getPackage().getName().startsWith("org.springframework");
        }
    };

    @ArchTest
    static final ArchRule Onion_architecture_is_respected = onionArchitecture()
            .domainModels("..domain.model..")
            .applicationServices("..application..")
            .adapter("app", "..adapter.app..")
            .adapter("persistence", "..adapter.persistence..")
            .adapter("rest", "..adapter.rest..");

    @ArchTest
    static final ArchRule Domain_is_not_annotated_by_spring_and_jpa = classes()
            .that()
            .resideInAPackage("..domain.model..")
            .should()
            .notBeAnnotatedWith(JAVAX_PERSISTENCE_ANNOTATIONS)
            .andShould()
            .notBeAnnotatedWith(SPRING_ANNOTATIONS);

    @ArchTest
    static final ArchRule Application_layer_is_not_annotated_by_spring_and_jpa = classes()
            .that()
            .resideInAPackage("..application..")
            .should()
            .notBeAnnotatedWith(JAVAX_PERSISTENCE_ANNOTATIONS)
            .andShould()
            .notBeAnnotatedWith(SPRING_ANNOTATIONS);
}
