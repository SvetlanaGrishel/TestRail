package pages;

import dto.TestCase;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import wrappers.Picklist;
import wrappers.TextArea;

@Log4j2
public class AddTestCasePage extends BasePage {

    private static final By TITLE_ADD_TEST_CASE_PAGE = By.xpath("//*[@id='content-header']/div/div[2]");
    private static final By TITLE_TEST_CASE = By.id("title");
    private static final By ADD_TEST_CASE_BUTTON = By.id("accept");
    private static final By ADD_NEXT_BUTTON = By.id("accept_and_next");
    private static final By CANCEL_TEST_CASE_BUTTON = By.xpath("//*[@id='form']/div[2]/a");
    private static final By MESSAGE_ABOUT_REQUIRED_FIELD = By.xpath("//div[text() = 'Field Title is a required field.']");

    public AddTestCasePage(WebDriver driver) {
        super(driver);
    }

    @Step("Fill all fields on 'Add Test Case' form")
    public AddTestCasePage fillAddTestCaseForm(TestCase testCase, String titleTestCase) {
        log.info("Fill all fields on 'Add Test Case' form");
        driver.findElement(TITLE_TEST_CASE).sendKeys(titleTestCase);
        new Picklist(driver, "Section").select(testCase.getSectionTestCase());
        new Picklist(driver, "Template").select(testCase.getTemplateTestCase());
        new Picklist(driver, "Type").select(testCase.getTypeTestCase());
        new Picklist(driver, "Priority").select(testCase.getPriorityTestCase());
        new Picklist(driver, "Automation Type").select(testCase.getAutomationTypeTestCase());
        new TextArea(driver, "Preconditions").write(testCase.getPreconditionsTestCase());
        new TextArea(driver, "Steps").write(testCase.getStepsTestCase());
        new TextArea(driver, "Expected Result").write(testCase.getExpectedResultTestCase());
        driver.findElement(ADD_TEST_CASE_BUTTON).click();
        return this;
    }

    @Step("Fill title of Test Case only")
    public void fillTestCaseTitle(String titleTestCase) {
        log.info("Fill title of Test Case only");
        driver.findElement(TITLE_TEST_CASE).sendKeys(titleTestCase);
    }

    @Step("Click 'Save Test Case' button")
    public void clickSaveTestCase() {
        log.info("Click 'Save Test Case' button");
        driver.findElement(ADD_TEST_CASE_BUTTON).click();
    }

    @Step("Check message about required field for title")
    public static String getMessageAboutRequiredField() {
        log.info("Check message about required field for title");
        return driver.findElement(MESSAGE_ABOUT_REQUIRED_FIELD).getText();
    }

    @Override
    public AddTestCasePage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE_ADD_TEST_CASE_PAGE));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("'Add Project' page isn't opened");
        }
        return this;
    }

    @Override
    public AddTestCasePage open() {
        return null;
    }
}
