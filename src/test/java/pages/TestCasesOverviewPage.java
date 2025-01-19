package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class TestCasesOverviewPage extends BasePage {

    private static final By TEST_CASE_TITLE_LINK = By.xpath("//td[4]/a[1]/span");
    private static final By TEST_CASES_OVERVIEW_PAGE_TITLE = By.xpath("//*[@id='content-header']/div/div[3]");
    private static final By CHECKBOX_SELECT_ALL_CASES = By.xpath("//*/tbody/tr[1]/th[2]/input");
    private static final By DELETE_TEST_CASE_BUTTON = By.xpath("//*[@id='deleteCases']/span");
    private static final By ICON_WITH_ZERO_TEST_CASES_QUANTITY = By.xpath("//span[text() = '0']");


    public TestCasesOverviewPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open 'Test Case Details' page")
    public void openTestCaseDetailsPage() {
        log.info("Open 'Test Case Details' page");
        driver.findElement(TEST_CASE_TITLE_LINK).click();
    }

    @Step("Mark checkbox to select all Test Cases")
    public void markCheckboxToSelectAllTestCases() {
        log.info("Mark checkbox to select all Test Cases");
        driver.findElement(CHECKBOX_SELECT_ALL_CASES).click();
    }

    @Step("Click 'Delete' button for Test Case")
    public DeleteTestCaseModal clickDeleteButtonForTestCases() {
        log.info("Click 'Delete' button for Test Case");
        driver.findElement(DELETE_TEST_CASE_BUTTON).click();
        return new DeleteTestCaseModal(driver);
    }

    @Step("Check that the icon for Test Cases quantity = 0")
    public boolean checkIconTestCasesQuantity() {
        log.info("Check that the icon for Test Cases quantity = 0");
        boolean checkIconTestCasesQuantity = driver.findElement(ICON_WITH_ZERO_TEST_CASES_QUANTITY).isDisplayed();
        return checkIconTestCasesQuantity;
    }

    @Override
    @Step("Open 'Test Cases Overview' page")
    public TestCasesOverviewPage open() {
        return null;
    }

    @Override
    @Step("Check that 'Test Cases Overview' page is opened")
    public TestCasesOverviewPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(TEST_CASES_OVERVIEW_PAGE_TITLE));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("'Add Project' page isn't opened");
        }
        return this;
    }
}
