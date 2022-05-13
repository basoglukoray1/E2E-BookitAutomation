package com.bookit.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {

                "html:target/cucumber-report.html",
                "rerun:target/rerun.txt",
                "me.jvt.cucumber.report.PrettyReports:target/cucumber",
                "json:target/cucumber.json",
                "junit:target/junit/junit-report.xml",
        },
        features = "src/test/resources/features/",
        glue = "com/bookit/step_definitions/",
        dryRun = true,
        tags = "@pair1"

)

public class CukesRunner {
}
