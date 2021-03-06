import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"ru.appline.autotests.steps"},
        plugin = {"io.qameta.allure.cucumber3jvm.AllureCucumber3Jvm"}
        //tags = {"@scenario2"}

)
public class CucumberRunner {
}
