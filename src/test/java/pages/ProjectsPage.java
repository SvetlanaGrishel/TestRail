package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ProjectsPage extends BasePage {

    private static final String PROJECTS_PAGE_URL = "https://sgtestrail.testrail.io/index.php?/admin/projects/overview";
    private static final By TITLE_PROJECTS_PAGE = By.xpath("//*[@id='content-header']/div/div[2]");
    private final By PROJECT_NAME_PATTERN = By.xpath("//ancestor::tr");

    public ProjectsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Check that the created project is present on 'Projects' page")
    public boolean isProjectFound(String projectTitle) {
        log.info("Check that the project is present on 'Projects' page");
        List<WebElement> projectTitles = driver.findElements(PROJECT_NAME_PATTERN);
        boolean isProjectFound = false;
        for (WebElement nameProject : projectTitles) {
            if (nameProject.getText().equals(projectTitle)) {
                isProjectFound = true;
                break;
            }
        }
        return isProjectFound;
    }

    @Override
    @Step("Check that 'Projects' page is opened")
    public ProjectsPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE_PROJECTS_PAGE));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("'Add Project' page isn't opened");
        }
        return this;
    }

    @Override
    @Step("Open 'Projects' page")
    public ProjectsPage open() {
        log.info("Open 'Projects' page");
        driver.get(PROJECTS_PAGE_URL);
        return this;
    }
}
