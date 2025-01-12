package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class AddProjectPage extends BasePage {

    private static final String BASE_ADD_PROJECT_URL = "https://sgtestrail.testrail.io/index.php?/admin/projects/add/";
    private static final By TITLE_ADD_PROJECT_PAGE = By.xpath("//*[@id='content-header']/div/div[2]");
    private static final By ADD_PROJECT_BUTTON = By.xpath("//*[@id='accept']");
    private static final By NAME_INPUT = By.id("name");
    private static final By PROJECT_TAB = By.id("projects-tabs-project");
    private static final By SUCCESS_MESSAGE_CREATED_PROJECT = By.xpath("//*[@id='content-inner']/div[1]");

    public AddProjectPage(WebDriver driver) {
        super(driver);
    }

    @Step("Fill the name of the project")
    public String fillProjectName(String projectName) {
        log.info("Fill the name of the project");
        driver.findElement(NAME_INPUT).sendKeys(projectName);
        return projectName;
    }

    @Step("Click 'Add Project' button")
    public Homepage clickAddProjectButton() {
        log.info("Click 'Add Project' button");
        driver.findElement(ADD_PROJECT_BUTTON).click();
        return new Homepage(driver);
    }

    @Step("Click 'Project' tab")
    public Homepage clickProjectTab() {
        log.info("Click 'Project' tab");
        driver.findElement(PROJECT_TAB).click();
        return new Homepage(driver);
    }

    @Step("Check message about successfully created project")
    public String getMessageForCreatedProject() {
        log.info("Check message about successfully created project");
        return driver.findElement(SUCCESS_MESSAGE_CREATED_PROJECT).getText();
    }

    @Override
    @Step("Open 'Add Project' page")
    public AddProjectPage open() {
        log.info("Open 'Add Project' page");
        driver.get(BASE_ADD_PROJECT_URL);
        return this;
    }

    @Override
    @Step("Check that 'Add Project' page is opened")
    public AddProjectPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE_ADD_PROJECT_PAGE));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("'Add Project' page isn't opened");
        }
        return this;
    }
}
