package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class TestCaseDetailsPage extends BasePage {

    private static final By MESSAGE_ABOUT_ADDED_TEST_CASE = By.xpath("//div[@data-testid='messageSuccessDivBox']");
    private static final By MESSAGE_ABOUT_UPDATED_TEST_CASE = By.xpath("//div[@data-testid='messageSuccessDivBox']");
    private static final By ADD_ANOTHER_LINK = By.xpath("//*[@id='content-inner']/div[1]/a");
    private static final By EDIT_TEST_CASE_BUTTON = By.xpath("//*[@id='content-header']/div/div[3]/a");
    private static final By TEST_CASES_BREADCRUMB = By.xpath("//*[@id='content']/div[2]/a");

    public TestCaseDetailsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Check message about successfully added test case")
    public static String getMessageForAddedTestCase() {
        log.info("Check message about successfully added test case");
        return driver.findElement(MESSAGE_ABOUT_ADDED_TEST_CASE).getText();
    }

    @Step("Check message about successfully updated test case")
    public static String getMessageForUpdatedTestCase() {
        log.info("Check message about successfully updated test case");
        return driver.findElement(MESSAGE_ABOUT_UPDATED_TEST_CASE).getText();
    }

    @Step("Click 'Add another' link")
    public void clickAddAnotherLink() {
        log.info("Click 'Add another' link");
        driver.findElement(ADD_ANOTHER_LINK).click();
    }

    @Step("Click 'Edit' for Test Case")
    public void clickEditIcon() {
        log.info("Click 'Edit' for Test Case");
        driver.findElement(EDIT_TEST_CASE_BUTTON).click();
    }

    @Step("Click 'Test Cases' breadcrumbs")
    public void clickTestCasesBreadcrumbs() {
        log.info("Click 'Test Cases' breadcrumbs");
        driver.findElement(TEST_CASES_BREADCRUMB).click();
    }

    @Override
    @Step("Open 'Test Case Details' page")
    public TestCaseDetailsPage open() {
        return null;
    }

    @Override
    @Step("Check that 'Test Case Details' page is opened")
    public TestCaseDetailsPage isPageOpened() {
        return null;
    }
}
