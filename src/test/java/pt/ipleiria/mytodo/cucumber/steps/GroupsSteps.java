package pt.ipleiria.mytodo.cucumber.steps;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
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

public class GroupsSteps {
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

    @Given("I have authenticated in the app")
    public void iHaveAuthenticatedInTheApp() throws Exception {
        driver = Utils.setUp("src/test/resources/pt/ipleiria/mytodo/cucumber/groups.conf.json", scenario, 1);
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


    @And("I tap the menu button")
    public void iTapTheMenuButton() throws Exception {
        Utils.sleep(1000);
        By by = By.xpath("//android.widget.ImageButton[@content-desc = 'Open navigation drawer']");
        Utils.waitForAndTouch(driver, by, WAITFOR);
    }


    @And("I tap the Groups menu item")
    public void iTapTheGroupsMenuItem()throws Exception {
        Utils.sleep(1000);
        By by = By.id("pt.ipleiria.mytodo:id/menu_item_groups");
        Utils.waitForAndTouch(driver, by, WAITFOR);
    }

    @Then("The list of groups must have a size greater than {int}")
    public void theListOfGroupsMustHaveASizeGreaterThan(int arg0) throws Exception {
        Utils.sleep(1000);
        By by = By.id("pt.ipleiria.mytodo:id/group_list_item");
        List<AndroidElement> list = Utils.waitFor(driver, by, WAITFOR);
        assertTrue(list.size() > arg0);
    }


    @When("I tap the Add button")
    public void iTapTheAddButton() throws Exception {
        Utils.sleep(1000);
        By by = By.id("pt.ipleiria.mytodo:id/action_add");
        Utils.waitForAndTouch(driver, by, WAITFOR);
    }

    @And("I wait to see the create dialog")
    public void iWaitToSeeTheCreateDialog() throws Exception {
        Utils.sleep(1000);
        By by = By.id("pt.ipleiria.mytodo:id/edit_group_dialog");
        Utils.waitForElement(driver, by, WAITFOR);
    }

    @And("I enter {string} into the name input")
    public void iEnterIntoTheNameInput(String arg0) throws Exception {
        Utils.sleep(1000);
        By by = By.id("pt.ipleiria.mytodo:id/group_name");
        AndroidElement el = Utils.waitForElement(driver, by, WAITFOR);
        el.sendKeys(arg0);
    }

    @And("I enter {string} into the members input")
    public void iEnterIntoTheMembersInput(String arg0) throws Exception {
        Utils.sleep(1000);
        By by = By.id("pt.ipleiria.mytodo:id/group_members");
        AndroidElement el = Utils.waitForElement(driver, by, WAITFOR);
        el.sendKeys(arg0);
    }

    @And("I tap the save button")
    public void iTapTheSaveButton() throws Exception {
        Utils.sleep(1000);
        By by = By.id("pt.ipleiria.mytodo:id/dialog_action_save");
        Utils.waitForAndTouch(driver, by, WAITFOR);
    }

    @Then("I should see {string} group in a groups list")
    public void iShouldSeeGroupInAGroupsList(String arg0) throws Exception{
        By by = By.xpath(String.format("//android.widget.TextView[@resource-id='pt.ipleiria.mytodo:id/group_list_item_name' and @text='%s']", arg0));
        List<AndroidElement> list = Utils.waitForSafe(driver, by, WAITFOR);
        assertTrue(list.size() > 0);
    }

    @Then("I should see a {string} message")
    public void iShouldSeeAMessage(String arg0) throws Exception {
        By by = By.xpath(String.format("//android.widget.Toast[@text='%s']", arg0));
        List<AndroidElement> list = Utils.waitForSafe(driver, by, WAITFOR);
        assertTrue(list.size() > 0);
    }

    @Then("I should see the save button in {string} state")
    public void iShouldSeeTheSaveButtonInState(String arg0) throws Exception {
        Utils.sleep(1000);
        By by = By.xpath(String.format("//android.widget.Button[@resource-id='pt.ipleiria.mytodo:id/dialog_action_save' and @enabled = '%s']", arg0));
        Utils.waitForElement(driver, by, WAITFOR);
    }

    @When("I tap in an edit icon of a group named {string}")
    public void iTapInAGroupNamed(String arg0) throws Exception {
        Utils.sleep(1000);
        By by = By.xpath(String.format("//android.widget.TextView[@resource-id='pt.ipleiria.mytodo:id/group_list_item_name' and @text='%s']/following-sibling::android.widget.ImageButton", arg0));
        Utils.waitForAndTouch(driver, by, WAITFOR);
    }

    @And("I wait to see the details dialog")
    public void iWaitToSeeTheDetailsDialog() throws Exception  {
        Utils.sleep(1000);
        By by = By.id("pt.ipleiria.mytodo:id/edit_group_dialog");
        Utils.waitForElement(driver, by, WAITFOR);
    }

    @And("I tap the delete button")
    public void iTapTheDeleteButton() throws Exception {
        Utils.sleep(1000);
        By by = By.id("pt.ipleiria.mytodo:id/dialog_action_delete");
        Utils.waitForAndTouch(driver, by, WAITFOR);
    }

    @And("I tap the Ok button in the confirmation dialog")
    public void iTapTheOkButtonInTheConfirmationDialog() throws Exception {
        Utils.sleep(1000);
        By by = By.id("android:id/button1");
        Utils.waitForAndTouch(driver, by, WAITFOR);
    }

    @Then("I should not see {string} group in a groups list")
    public void iShouldNotSeeGroupInAGroupsList(String arg0) throws Exception {
        By by = By.xpath(String.format("//android.widget.TextView[@resource-id='pt.ipleiria.mytodo:id/group_list_item_name' and @text='%s']", arg0));
        List<AndroidElement> list = Utils.waitForSafe(driver, by, WAITFOR);
        assertTrue(list.size() == 0);
    }

    @And("I should see {string} members in the group")
    public void iShouldSeeMembersInTheGroup(String arg0) throws Exception{
        Utils.sleep(1000);
        By by = By.xpath(String.format("//android.widget.EditText[@resource-id='pt.ipleiria.mytodo:id/group_members' and @text='%s']", arg0));
        Utils.waitForElement(driver, by, WAITFOR);
    }

    @And("I should {string} a clickable group with {string} name in a groups list")
    public void iShouldAGroupWithNameInAGroupsList(String arg0, String arg1) throws Exception {
        By by = By.xpath(String.format("//android.widget.TextView[@resource-id='pt.ipleiria.mytodo:id/group_list_item_name' and @clickable='true' and @text='%s']", arg1));
        List<AndroidElement> list = Utils.waitForSafe(driver, by, WAITFOR);
        int compareWith = arg0.equalsIgnoreCase("false") ? 0 : 1;
        assertTrue(list.size() == compareWith);
    }

    @Then("I should see the details dialog with a group named {string}")
    public void iShouldSeeTheDetailsDialogWithAGroupNamed(String arg0) throws Exception {
        Utils.sleep(1000);
        By by = By.xpath(String.format("//android.widget.EditText[@resource-id='pt.ipleiria.mytodo:id/group_name' and @text='%s']", arg0));
        Utils.waitForElement(driver, by, WAITFOR);
    }

}
