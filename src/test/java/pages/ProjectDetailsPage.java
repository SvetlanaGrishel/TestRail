package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class ProjectDetailsPage extends BasePage {

    private static final By ADD_TEST_CASE_LINK = By.id("sidebar-cases-add");
    private static final By VIEW_ALL_TEST_CASE_LINK = By.id("sidebar-suites-viewall");

    public ProjectDetailsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click 'Add' link for Test Cases block")
    public AddTestCasePage clickAddTestCaseLink() {
        log.info("Click 'Add' link for Test Cases block");
        driver.findElement(ADD_TEST_CASE_LINK).click();
        return new AddTestCasePage(driver);
    }

    @Step("Click 'View All' link for Test Cases block")
    public TestCaseDetailsPage clickViewAllTestCasesLink() {
        log.info("Click 'View All' link for Test Cases block");
        driver.findElement(VIEW_ALL_TEST_CASE_LINK).click();
        return new TestCaseDetailsPage(driver);
    }

    @Override
    @Step("Open 'Project Details' page")
    public ProjectDetailsPage open() {
        return null;
    }

    @Override
    @Step("Check that 'Project Details' page is opened")
    public ProjectDetailsPage isPageOpened() {
        return null;
    }
}
