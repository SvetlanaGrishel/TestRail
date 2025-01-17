package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class Homepage extends BasePage {

    private static final By TITLE_ALL_PROJECTS_PAGE = By.xpath("//*[@id='content-header']/div/div[2]");
    private static final String ALL_PROJECTS_PAGE_URL = "https://sgtestrail.testrail.io/index.php?/dashboard";
    private static final By ADD_PROJECT_BUTTON = By.id("sidebar-projects-add");
    private static final By ADMINISTRATION_LINK = By.id("navigation-admin");
    //private static final String PROJECT_TITLE_HOMEPAGE_PATTERN = "//tr[contains(@class,'project')]//child::a[text()='%s']";
    private static final String PROJECT_TITLE_HOMEPAGE_PATTERN = "//a[text() = '%s']";

    public Homepage(WebDriver driver) {
        super(driver);
    }

    @Step("Get title of the 'All Projects' page")
    public String getTitle() {
        log.info("Get title of the Homepage page");
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE_ALL_PROJECTS_PAGE));
        return driver.findElement(TITLE_ALL_PROJECTS_PAGE).getText();
    }

    @Step("Find 'Administration' link on 'Homepage'")
    public boolean findAdministrationLink() {
        log.info("Find 'Administration' link on 'Homepage'");
        boolean resultFindAdministrationLink = driver.findElement(ADMINISTRATION_LINK).isDisplayed();
        return resultFindAdministrationLink;
    }

    @Step("Find 'Add Project' button on 'Homepage'")
    public boolean findAddProjectButton() {
        log.info("Find 'Add Project' button on 'Homepage'");
        boolean resultFindAddProjectButton = driver.findElement(ADD_PROJECT_BUTTON).isDisplayed();
        return resultFindAddProjectButton;
    }

    //ПЕРЕПИСАТЬ (!!!)
    @Step("Open project details page from 'Homepage'")
    public ProjectDetailsPage openProjectDetailsPage(String projectName) {
        log.info("Open project details page from 'Homepage'");
        By projectNamePattern = By.xpath(String.format(PROJECT_TITLE_HOMEPAGE_PATTERN, projectName));
        driver.findElement(projectNamePattern).click();
        return new ProjectDetailsPage(driver);
    }

    @Override
    @Step("Check that Homepage page is opened")
    public Homepage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated((TITLE_ALL_PROJECTS_PAGE)));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("'Homepage' page isn't opened");
        }
        return this;
    }

    @Override
    @Step("Open 'Homepage' page")
    public Homepage open() {
        log.info("Open 'Homepage' page");
        driver.get(ALL_PROJECTS_PAGE_URL);
        return this;
    }
}
