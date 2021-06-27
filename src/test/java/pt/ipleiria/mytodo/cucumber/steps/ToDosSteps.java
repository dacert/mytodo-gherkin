package pt.ipleiria.mytodo.cucumber.steps;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import pt.ipleiria.mytodo.cucumber.ProjectParameters;
import pt.ipleiria.mytodo.cucumber.Utils;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class ToDosSteps {
    public AndroidDriver<AndroidElement> driver;
    Scenario scenario;
    int WAITFOR = 10;

    @Before
    public void setUp(Scenario scenario) {
        this.scenario = scenario;
    }

    @After
    public void tearDown() throws Exception {
        // Invoke driver.quit() to indicate that the test is completed.
        // Otherwise, it will appear as timed out on BrowserStack.
        if(driver != null)
            driver.quit();
    }

    @Given("I've logged on the application")
    public void iVeLoggedOnTheApplication() throws Exception {
        driver = Utils.setUp("src/test/resources/pt/ipleiria/mytodo/cucumber/todos.conf.json", scenario,1);
        By by;

        // 2. Type '{{User}}' in 'login_email'
        Utils.sleep(500);
        by = By.id("pt.ipleiria.mytodo:id/login_email");
        Utils.waitForElement(driver, by, WAITFOR).sendKeys(ProjectParameters.User);

        // 3. Type '{{Pass}}' in 'login_pass'
        Utils.sleep(500);
        by = By.id("pt.ipleiria.mytodo:id/login_pass");
        Utils.waitForElement(driver, by, WAITFOR).sendKeys(ProjectParameters.Pass);

        // 4. Tap 'LOGIN'
        Utils.sleep(500);
        by = By.id("pt.ipleiria.mytodo:id/login_btn");
        Utils.waitForAndTouch(driver, by, WAITFOR);
    }

    @And("I click the menu button")
    public void iClickTheMenuButton() throws Exception {
        Utils.sleep(1000);
        By by = By.xpath("//android.widget.ImageButton[@content-desc = 'Open navigation drawer']");
        Utils.waitForAndTouch(driver, by, WAITFOR);
    }

    @And("I click the Groups menu item")
    public void iClickTheGroupsMenuItem() throws Exception {
        Utils.sleep(1000);
        By by = By.id("pt.ipleiria.mytodo:id/menu_item_groups");
        Utils.waitForAndTouch(driver, by, WAITFOR);
    }

    @And("I click in an edit icon of a group named {string}")
    public void iClickInAnEditIconOfAGroupNamed(String arg0) throws Exception {
        Utils.sleep(1000);
        By by = By.xpath(String.format("//android.widget.TextView[@resource-id='pt.ipleiria.mytodo:id/group_list_item_name' and @text='%s']", arg0));
        Utils.waitForAndTouch(driver, by, WAITFOR);
    }

    @Then("The list of to-dos must have a size greater than {int}")
    public void theListOfToDosMustHaveASizeGreaterThan(int arg0) throws Exception {
        Utils.sleep(1000);
        By by = By.id("pt.ipleiria.mytodo:id/todo_list_item");
        List<AndroidElement> list = Utils.waitForSafe(driver, by, WAITFOR);
        assertTrue(list.size() > arg0);
    }

}