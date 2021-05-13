package pt.ipleiria.mytodo.cucumber;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileReader;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

public class GroupsStep {
    public AndroidDriver<AndroidElement> driver;

    @Before
    public void setUp() throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject config = (JSONObject) parser.parse(new FileReader("src/test/resources/pt/ipleiria/mytodo/run_parallel_test/parallel.conf.json"));
        JSONArray envs = (JSONArray) config.get("environments");

        DesiredCapabilities capabilities = new DesiredCapabilities();

        Map<String, String> envCapabilities = (Map<String, String>) envs.get(Integer.parseInt("1")); //selecting device index
        Iterator it = envCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
        }

        Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
        it = commonCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if(capabilities.getCapability(pair.getKey().toString()) == null){
                capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
            }
        }

        String username = System.getenv("BROWSERSTACK_USERNAME");
        if(username == null) {
            username = (String) config.get("username");
        }

        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
        if(accessKey == null) {
            accessKey = (String) config.get("access_key");
        }

        String app = System.getenv("BROWSERSTACK_APP_ID");
        if(app != null && !app.isEmpty()) {
            capabilities.setCapability("app", app);
        }

        driver = new AndroidDriver(new URL("http://"+username+":"+accessKey+"@"+config.get("server")+"/wd/hub"), capabilities);
    }

    @After
    public void tearDown() throws Exception {
        // Invoke driver.quit() to indicate that the test is completed.
        // Otherwise, it will appear as timed out on BrowserStack.
        driver.quit();
    }

    @Given("I have authenticated in the app")
    public void iHaveAuthenticatedInTheApp() {
    }

    @When("I go to Add group screen")
    public void iGoToAddGroupScreen() {
    }

    @And("I enter {string} into the name input")
    public void iEnterFooIntoTheNameInput() {
    }

    @And("I tap the {string} button")
    public void iTapTheCreateButton() {
    }

    @Then("I should see a susses message")
    public void iShouldSeeASussesMessage() {
    }

    @Then("I should see a {string} message")
    public void iShouldSeeANameIsDuplicatedMessage() {
    }

    @Then("I should see an error text <Invalid>")
    public void iShouldSeeAnErrorTextInvalid() {
    }

    @When("I go to Groups screen")
    public void iGoToGroupsScreen() {
    }

    @Then("The list of groups must have a size greater than {int}")
    public void theListOfGroupsMustHaveASizeGreaterThan(int arg0) {
    }

    @And("I tap in a {string} group")
    public void iTapInADetailsGroup() {
    }

    @Then("I should see the Details screen of {string} group")
    public void iShouldSeeTheDetailsScreenOfDetailsGroup() {
    }

    @And("I enter {string} into the member input")
    public void iEnterEmailDominioComMailDominioComIntoTheMemberInput(int arg0) {
    }

    @And("I tap the {string} button in the confirmation dialog")
    public void iTapTheOkButtonInTheConfirmationDialog() {
    }
}
