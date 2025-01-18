package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

@Log4j2
public class TestCaseDetailsPage extends BasePage{

    private static final By MESSAGE_ABOUT_ADDED_TEST_CASE = By.xpath("//div[@data-testid='messageSuccessDivBox']");
    private static final By ADD_ANOTHER_LINK = By.xpath("//*[@id='content-inner']/div[1]/a");

    public TestCaseDetailsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Check message about successfully added test case")
    public static String getMessageForAddedTestCase() {
        log.info("Check message about successfully added test case");
        return driver.findElement(MESSAGE_ABOUT_ADDED_TEST_CASE).getText();
    }

    @Step("Click 'Add another' link")
    public void clickAddAnotherLink() {
        log.info("Click 'Add another' link");
        driver.findElement(ADD_ANOTHER_LINK).click();
    }

    @Override
    public BasePage isPageOpened() {
        return null;
    }

    @Override
    public BasePage open() {
        return null;
    }
}
