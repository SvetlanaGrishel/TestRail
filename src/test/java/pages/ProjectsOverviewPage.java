package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

@Log4j2
public class ProjectsOverviewPage extends BasePage {

    private static final String PROJECTS_PAGE_URL = "https://sgtestrail.testrail.io/index.php?/admin/projects/overview";
    private static final By TITLE_PROJECTS_PAGE = By.xpath("//*[@id='content-header']/div/div[2]");
    private final By PROJECT_NAME_PATTERN = By.xpath("//a//ancestor::tr//td//a");
    private final String DELETE_PROJECT_PATTERN = "//a[text()= '%s']//ancestor::tr//div[@data-testid='projectDeleteButton']";
    private static final By SUCCESS_MESSAGE_UPDATED_PROJECT = By.xpath("//*[@id='content-inner']/div[1]");
    private static final By SUCCESS_MESSAGE_DELETED_PROJECT = By.xpath("//*[@id='content-inner']/div[1]");

    public ProjectsOverviewPage(WebDriver driver) {
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

    @Step("Open 'Edit' project page")
    public void openEditProjectPage() {
        log.info("Open 'Edit' project page");
        driver.findElement(PROJECT_NAME_PATTERN).click();
    }

    @Step("Check message about successfully updated project")
    public String getMessageForUpdatedProject() {
        log.info("Check message about successfully updated project");
        return driver.findElement(SUCCESS_MESSAGE_UPDATED_PROJECT).getText();
    }

    @Step("Click 'X' icon for project to delete")
    public DeleteProjectModal clickIconToDeleteProject(String projectName) {
        log.info("Click 'X' icon for project to delete");
        //driver.findElement(PROJECT_NAME_PATTERN).click();
        By deleteProjectName = By.xpath(String.format(DELETE_PROJECT_PATTERN, projectName));
        driver.findElement(deleteProjectName).click();
        return new DeleteProjectModal(driver);
    }

    @Step("Check message about successfully deleted project")
    public String getMessageForDeletedProject() {
        log.info("Check message about successfully deleted project");
        return driver.findElement(SUCCESS_MESSAGE_DELETED_PROJECT).getText();
    }

    @Override
    @Step("Check that 'Projects' page is opened")
    public ProjectsOverviewPage isPageOpened() {
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
    public ProjectsOverviewPage open() {
        log.info("Open 'Projects' page");
        driver.get(PROJECTS_PAGE_URL);
        return this;
    }
}
