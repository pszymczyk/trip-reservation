package com.pszymczyk;

import org.junit.Test;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaAnnotation;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class ArchitectureSpec {

    private static final DescribedPredicate<JavaAnnotation> SPRING_ANNOTATIONS = new DescribedPredicate<JavaAnnotation>("annotation from org.springframework") {
        @Override
        public boolean apply(JavaAnnotation input) {
            return input.getRawType()
                         .getPackage()
                         .getName()
                         .startsWith("org.springframework");
        }
    };

    private static final DescribedPredicate<JavaAnnotation> JAVAX_PERSISTENCE_ANNOTATIONS = new DescribedPredicate<JavaAnnotation>("annotation from javax.persistence") {
        @Override
        public boolean apply(JavaAnnotation input) {
            return input.getRawType()
                         .getPackage()
                         .getName()
                         .startsWith("javax.persistence");
        }
    };

    JavaClasses importedClasses = new ClassFileImporter().withImportOption(location -> !location.contains("/test/"))
                                                         .importPackages("com.pszymczyk");

    @Test
    public void domainShouldNotDependOnApplicationAndPorts() {
        //when
        ArchRule rule = noClasses().that()
                                 .resideInAPackage("com.pszymczyk.domain")
                                 .should()
                                 .dependOnClassesThat()
                                 .resideInAnyPackage("com.pszymczyk.application..", "com.pszymczyk.adapters..");

        //then
        rule.check(importedClasses);
    }

    @Test
    public void applicationShouldOnlyDependOnDomain() {
        //when
        ArchRule rule = classes().that()
                                 .resideInAPackage("com.pszymczyk.application")
                                 .should()
                                 .onlyDependOnClassesThat()
                                 .resideInAnyPackage("com.pszymczyk.domain..", "java..");

        //then
        rule.check(importedClasses);
    }

    @Test
    public void domainShouldNotBeAnnotatedWithJavaxPersistence() {
        //when
        ArchRule rule = classes().that()
                                 .resideInAPackage("com.pszymczyk.domain")
                                 .should()
                                 .notBeAnnotatedWith(JAVAX_PERSISTENCE_ANNOTATIONS);

        //then
        rule.check(importedClasses);
    }

    @Test
    public void applicationShouldNotBeAnnotatedWithSpring() {
        //when
        ArchRule rule = classes().that()
                                 .resideInAPackage("com.pszymczyk.application")
                                 .should()
                                 .notBeAnnotatedWith(SPRING_ANNOTATIONS);

        //then
        rule.check(importedClasses);
    }
}
