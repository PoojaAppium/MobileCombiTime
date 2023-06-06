package TestRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;



@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/Feature" , 
glue={"StepsDefinition"},
monochrome=true , 
dryRun=false,
plugin= {"pretty" , "html:target/HtmlReport" },
tags= {"@TTag4"}
)
public class RunTest {
	
	
}

