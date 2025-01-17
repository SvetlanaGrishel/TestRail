package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class ProjectDetailsPage extends BasePage {

    private final By ADD_TEST_CASE_LINK = By.id("sidebar-cases-add");

    public ProjectDetailsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click 'Add' link for Test Case block")
    public AddTestCasePage clickAddTestCaseLink() {
        log.info("Click 'Add' link for Test Case block");
        driver.findElement(ADD_TEST_CASE_LINK).click();
        return new AddTestCasePage(driver);
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
