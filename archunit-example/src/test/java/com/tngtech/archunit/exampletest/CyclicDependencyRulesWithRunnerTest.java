package com.tngtech.archunit.exampletest;

import com.tngtech.archunit.junit.AnalyseClasses;
import com.tngtech.archunit.junit.ArchIgnore;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.slices.Slice;
import com.tngtech.archunit.library.slices.Slices;
import org.junit.runner.RunWith;

import static com.tngtech.archunit.lang.ArchRule.rule;
import static com.tngtech.archunit.library.DependencyRules.noCycles;

@ArchIgnore
@RunWith(ArchUnitRunner.class)
@AnalyseClasses(packages = "com.tngtech.archunit.example.cycle")
public class CyclicDependencyRulesWithRunnerTest {

    @ArchTest
    public static final ArchRule<Slice> NO_CYCLES_BY_METHOD_CALLS_BETWEEN_SLICES =
            rule(Slices.matching("..simplecycle.(*)..").namingSlices("CycleSlice $1"))
                    .should("not contain cycles")
                    .assertedBy(noCycles());

    @ArchTest
    public static final ArchRule<Slice> NO_CYCLES_BY_CONSTRUCTOR_CALLS_BETWEEN_SLICES =
            rule(Slices.matching("..constructorcycle.(*)..").namingSlices("CycleSlice $1"))
                    .should("not contain cycles")
                    .assertedBy(noCycles());

    @ArchTest
    public static final ArchRule<Slice> NO_CYCLES_BY_INHERITANCE_BETWEEN_SLICES =
            rule(Slices.matching("..(inheritancecycle).(*)..").namingSlices("$2 of $1"))
                    .should("not contain cycles")
                    .assertedBy(noCycles());

    @ArchTest
    public static final ArchRule<Slice> NO_CYCLES_BY_FIELD_ACCESS_BETWEEN_SLICES =
            rule(Slices.matching("..(fieldaccesscycle).(*)..").namingSlices("$2 of $1"))
                    .should("not contain cycles")
                    .assertedBy(noCycles());

    @ArchTest
    public static final ArchRule<Slice> NO_CYCLES_IN_SIMPLE_SCENARIO =
            rule(Slices.matching("..simplescenario.(*)..").namingSlices("$1"))
                    .should("not contain cycles")
                    .assertedBy(noCycles());

    @ArchTest
    public static final ArchRule<Slice> NO_CYCLES_IN_COMPLEX_SCENARIO =
            rule(Slices.matching("..(complexcycles).(*)..").namingSlices("$2 of $1"))
                    .should("not contain cycles")
                    .assertedBy(noCycles());
}