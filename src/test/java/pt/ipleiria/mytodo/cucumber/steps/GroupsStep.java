package pt.ipleiria.mytodo.cucumber.steps;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import pt.ipleiria.mytodo.cucumber.ProjectParameters;
import pt.ipleiria.mytodo.cucumber.Utils;

import java.util.List;
import static org.junit.Assert.assertTrue;


public class GroupsStep {
    public AndroidDriver<AndroidElement> driver;

    @Before
    public void setUp() throws Exception {
        driver = Utils.setUp("src/test/resources/pt/ipleiria/mytodo/cucumber/groups.conf.json", 1);
    }

    @After
    public void tearDown() throws Exception {
        // Invoke driver.quit() to indicate that the test is completed.
        // Otherwise, it will appear as timed out on BrowserStack.
        driver.quit();
    }

    @Given("I have authenticated in the app")
    public void iHaveAuthenticatedInTheApp() throws Exception {
        By by;

        // 2. Type '{{User}}' in 'login_email'
        Utils.sleep(500);
        by = By.id("pt.ipleiria.mytodo:id/login_email");
        driver.findElement(by).sendKeys(ProjectParameters.User);

        // 3. Type '{{Pass}}' in 'login_pass'
        Utils.sleep(500);
        by = By.id("pt.ipleiria.mytodo:id/login_pass");
        driver.findElement(by).sendKeys(ProjectParameters.Pass);

        // 4. Tap 'LOGIN'
        Utils.sleep(500);
        by = By.id("pt.ipleiria.mytodo:id/login_btn");
        (new TouchAction((driver))).tap(TapOptions.tapOptions().withElement(ElementOption.element((driver).findElement(by)))).perform();

    }


    @And("I tap the menu button")
    public void iTapTheMenuButton() throws Exception {
        Utils.sleep(1000);
        By by = By.xpath("//android.widget.ImageButton[@content-desc = 'Open navigation drawer']");
        (new TouchAction((driver))).tap(TapOptions.tapOptions().withElement(ElementOption.element((driver).findElement(by)))).perform();
    }


    @And("I tap the Groups menu item")
    public void iTapTheGroupsMenuItem()throws Exception {
        Utils.sleep(1000);
        By by = By.id("pt.ipleiria.mytodo:id/menu_item_groups");
        (new TouchAction((driver))).tap(TapOptions.tapOptions().withElement(ElementOption.element((driver).findElement(by)))).perform();
    }

    @Then("The list of groups must have a size greater than {int}")
    public void theListOfGroupsMustHaveASizeGreaterThan(int arg0) throws Exception {
        Utils.sleep(1000);
        By by = By.id("pt.ipleiria.mytodo:id/group_list_item");
        List<AndroidElement> list = driver.findElements(by);
        assertTrue(list.size() > arg0);
    }


    @When("I tap the Add button")
    public void iTapTheAddButton() throws Exception {
        Utils.sleep(1000);
        By by = By.id("pt.ipleiria.mytodo:id/action_add");
        (new TouchAction((driver))).tap(TapOptions.tapOptions().withElement(ElementOption.element((driver).findElement(by)))).perform();
    }

    @And("I wait to see the create dialog")
    public void iWaitToSeeTheCreateDialog() throws Exception {
        Utils.sleep(1000);
        By by = By.id("pt.ipleiria.mytodo:id/edit_group_dialog");
        driver.findElement(by);
    }

    @And("I enter {string} into the name input")
    public void iEnterIntoTheNameInput(String arg0) throws Exception {
        Utils.sleep(1000);
        By by = By.id("pt.ipleiria.mytodo:id/group_name");
        driver.findElement(by).sendKeys(arg0);
    }

    @And("I enter {string} into the member input")
    public void iEnterIntoTheMemberInput(String arg0) throws Exception {
        Utils.sleep(1000);
        By by = By.id("pt.ipleiria.mytodo:id/group_members");
        driver.findElement(by).sendKeys(arg0);
    }

    @And("I tap the save button")
    public void iTapTheSaveButton() throws Exception {
        Utils.sleep(1000);
        By by = By.id("pt.ipleiria.mytodo:id/save_group");
        (new TouchAction((driver))).tap(TapOptions.tapOptions().withElement(ElementOption.element((driver).findElement(by)))).perform();
    }

    @Then("I should see {string} group in a groups list")
    public void iShouldSeeGroupInAGroupsList(String arg0) throws Exception{
        By by = By.xpath(String.format("//android.widget.TextView[@resource-id='pt.ipleiria.mytodo:id/group_list_item_name' and @text='%s']", arg0));
        int loops = 10;
        List<AndroidElement> list = driver.findElements(by);
        while (list.size() == 0 && loops > 0){
            Utils.sleep(500);
            list = driver.findElements(by);
            loops --;
        }
        assertTrue(list.size() > 0);
    }

    @Then("I should see a {string} message")
    public void iShouldSeeAMessage(String arg0) throws Exception {
        By by = By.xpath(String.format("//android.widget.Toast[@text='%s']", arg0));
        int loops = 10;
        List<AndroidElement> list = driver.findElements(by);
        while (list.size() == 0 && loops > 0){
            Utils.sleep(500);
            list = driver.findElements(by);
            loops --;
        }
        assertTrue(list.size() > 0);
    }

    @Then("I should see the save button in {string} state")
    public void iShouldSeeTheSaveButtonInState(String arg0) throws Exception {
        Utils.sleep(1000);
        By by = By.xpath(String.format("//android.widget.Button[@resource-id='pt.ipleiria.mytodo:id/save_group' and @enabled = '%s']", arg0));
        driver.findElement(by);
    }

    @When("I tap in a {string} group")
    public void iTapInAGroup(String arg0) throws Exception  {
        Utils.sleep(1000);
        By by = By.xpath(String.format("//android.widget.TextView[@resource-id='pt.ipleiria.mytodo:id/group_list_item_name' and @text='%s']", arg0));
        (new TouchAction((driver))).tap(TapOptions.tapOptions().withElement(ElementOption.element((driver).findElement(by)))).perform();
    }

    @And("I wait to see the details dialog")
    public void iWaitToSeeTheDetailsDialog() throws Exception  {
        Utils.sleep(1000);
        By by = By.id("pt.ipleiria.mytodo:id/edit_group_dialog");
        driver.findElement(by);
    }

    @And("I tap the delete button")
    public void iTapTheDeleteButton() throws Exception {
        Utils.sleep(1000);
        By by = By.id("pt.ipleiria.mytodo:id/delete_group");
        (new TouchAction((driver))).tap(TapOptions.tapOptions().withElement(ElementOption.element((driver).findElement(by)))).perform();
    }

    @And("I tap the Ok button in the confirmation dialog")
    public void iTapTheOkButtonInTheConfirmationDialog() throws Exception {
        Utils.sleep(1000);
        By by = By.id("pt.ipleiria.mytodo:id/action_ok");
        (new TouchAction((driver))).tap(TapOptions.tapOptions().withElement(ElementOption.element((driver).findElement(by)))).perform();
    }

    @Then("I should not see {string} group in a groups list")
    public void iShouldNotSeeGroupInAGroupsList(String arg0) throws Exception {
        By by = By.xpath(String.format("//android.widget.TextView[@resource-id='pt.ipleiria.mytodo:id/group_list_item_name' and @text='%s']", arg0));
        int loops = 10;
        List<AndroidElement> list = driver.findElements(by);
        while (list.size() == 0 && loops > 0){
            Utils.sleep(500);
            list = driver.findElements(by);
            loops --;
        }
        assertTrue(list.size() == 0);
    }
}
