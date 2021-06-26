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


public class ToDosSteps {
    public AndroidDriver<AndroidElement> driver;

    @Before
    public void setUp() throws Exception {
        driver = Utils.setUp("src/test/resources/pt/ipleiria/mytodo/cucumber/todos.conf.json", 1);
    }

    @After
    public void tearDown() throws Exception {
        // Invoke driver.quit() to indicate that the test is completed.
        // Otherwise, it will appear as timed out on BrowserStack.
        driver.quit();
    }

    @Given("I have authenticated in the app to see my to-dos")
    public void iHaveAuthenticatedInTheAppToSeeMyToDos() throws Exception {
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

    @Then("The list of to-dos must have a size greater than {int}")
    public void theListOfToDosMustHaveASizeGreaterThan(int arg0) throws Exception {
        Utils.sleep(1000);
        By by = By.id("pt.ipleiria.mytodo:id/todo_list_item");
        List<AndroidElement> list = driver.findElements(by);
        assertTrue(list.size() > arg0);
    }
}
