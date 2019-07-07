package com.pszymczyk;

import org.junit.Test;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaAnnotation;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class ArchitectureSpec {

    private static final DescribedPredicate<JavaAnnotation> SPRING_ANNOTATIONS = new DescribedPredicate<JavaAnnotation>("annotation from org.springframework") {
        @Override
        public boolean apply(JavaAnnotation input) {
            return !input.getClass()
                         .getPackage()
                         .getName()
                         .startsWith("org.springframework");
        }
    };
    JavaClasses importedClasses = new ClassFileImporter().withImportOption(location -> !location.contains("/test/"))
                                                         .importPackages("com.pszymczyk");

    @Test
    public void domainShouldNotDependOnApplicationAndPorts() {
        //when
        ArchRule rule = classes().that()
                                 .resideInAPackage("com.pszymczyk.domain")
                                 .should()
                                 .onlyAccessClassesThat()
                                 .resideInAnyPackage("com.pszymczyk.domain..", "java..")
                                 .andShould()
                                 .notBeAnnotatedWith(SPRING_ANNOTATIONS);

        //then
        rule.check(importedClasses);
    }

    @Test
    public void applicationShouldOnlyDependOnDomain() {
        //when
        ArchRule rule = classes().that()
                                 .resideInAPackage("com.pszymczyk.application")
                                 .should()
                                 .onlyAccessClassesThat()
                                 .resideInAnyPackage("com.pszymczyk.domain..", "java..")
                                 .andShould()
                                 .notBeAnnotatedWith(SPRING_ANNOTATIONS);

        //then
        rule.check(importedClasses);
    }
}
