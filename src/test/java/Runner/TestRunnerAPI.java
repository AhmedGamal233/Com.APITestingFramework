package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features" //this where feature file exist
        ,glue= {"Runner","classpath/stepDefinitions"} //this is runner class package name
        ,plugin= {"pretty","html:target/cucumber-html-report,json:target/cucumber-html-reports/cucumber.json"}
        ,tags = "(not @bug) and (not @needToValidate) and (not @ignore)") //to make html report
public class TestRunnerAPI
{
}
