package pt.ipleiria.mytodo.cucumber.steps;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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

    @When("I tap the Add to-do button")
    public void iTapTheAddToDoButton() throws Exception {
        Utils.sleep(1000);
        By by = By.id("pt.ipleiria.mytodo:id/action_add_todo");
        Utils.waitForAndTouch(driver, by, WAITFOR);
    }

    @And("I wait to see the to-do create dialog")
    public void iWaitToSeeTheToDoCreateDialog() throws Exception {
        Utils.sleep(1000);
        By by = By.id("pt.ipleiria.mytodo:id/edit_todo_dialog");
        Utils.waitForElement(driver, by, WAITFOR);
    }

    @And("I enter {string} into the text input")
    public void iEnterIntoTheTextInput(String arg0) throws Exception {
        Utils.sleep(1000);
        By by = By.id("pt.ipleiria.mytodo:id/todo_text");
        AndroidElement el = Utils.waitForElement(driver, by, WAITFOR);
        el.sendKeys(arg0);
    }

    @And("I tap the save to-do button")
    public void iTapTheSaveToDoButton() throws Exception {
        Utils.sleep(1000);
        By by = By.id("pt.ipleiria.mytodo:id/save_todo");
        Utils.waitForAndTouch(driver, by, WAITFOR);
    }

    @Then("I should see a {string} to-do in a to-dos list")
    public void iShouldSeeAEnabledToDoInAToDosList(String arg0) throws Exception {
        By by = By.xpath(String.format("//android.widget.TextView[@resource-id='pt.ipleiria.mytodo:id/todo_list_item_text' and @text='%s']", arg0));
        List<AndroidElement> list = Utils.waitForSafe(driver, by, WAITFOR);
        assertTrue(list.size() > 0);
    }

    @When("I tap in a {string} to-do")
    public void iTapInAToDo(String arg0) throws Exception {
        Utils.sleep(1000);
        By by = By.xpath(String.format("//android.widget.TextView[@resource-id='pt.ipleiria.mytodo:id/todo_list_item_text' and @text='%s']", arg0));
        Utils.waitForAndTouch(driver, by, WAITFOR);
    }

    @And("I wait to see the to-do edit dialog")
    public void iWaitToSeeTheToDoEditDialog() throws Exception {
        iWaitToSeeTheToDoCreateDialog();
    }

    @Then("I should see the save to-do button in {string} state")
    public void iShouldSeeTheSaveToDoButtonInState(String arg0) throws Exception {
        Utils.sleep(1000);
        By by = By.xpath(String.format("//android.widget.Button[@resource-id='pt.ipleiria.mytodo:id/save_todo' and @enabled = '%s']", arg0));
        Utils.waitForElement(driver, by, WAITFOR);
    }

    @And("I tap the delete to-do button")
    public void iTapTheDeleteToDoButton() throws Exception {
        Utils.sleep(1000);
        By by = By.id("pt.ipleiria.mytodo:id/delete_todo");
        Utils.waitForAndTouch(driver, by, WAITFOR);
    }

    @And("I tap the Ok button in the delete confirmation dialog")
    public void iTapTheOkButtonInTheDeleteConfirmationDialog() throws Exception {
        Utils.sleep(1000);
        By by = By.id("android:id/button1");
        Utils.waitForAndTouch(driver, by, WAITFOR);
    }

    @Then("I should not see {string} to-do in a to-dos list")
    public void iShouldNotSeeToDoInAToDosList(String arg0) throws Exception {
        By by = By.xpath(String.format("//android.widget.TextView[@resource-id='pt.ipleiria.mytodo:id/todo_list_item_text' and @text='%s']", arg0));
        List<AndroidElement> list = Utils.waitForSafe(driver, by, WAITFOR);
        assertTrue(list.size() == 0);
    }
}
