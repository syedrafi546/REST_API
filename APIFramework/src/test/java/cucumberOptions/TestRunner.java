package cucumberOptions;

import org.junit.runner.RunWith;

 
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
 
//@RunWith (Cucumber.class)
@CucumberOptions(
		features="src/test/java/features",
		glue="stepDefinations"
		)
public class TestRunner extends AbstractTestNGCucumberTests{
	//
}
