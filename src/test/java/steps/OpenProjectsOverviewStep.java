package steps;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.ProjectsOverviewPage;

@Log4j2
public class OpenProjectsOverviewStep {

    ProjectsOverviewPage projectsOverviewPage;

    public OpenProjectsOverviewStep(WebDriver driver) {
        projectsOverviewPage = new ProjectsOverviewPage(driver);
    }

    public void openProjectsOverviewStep() {
        log.info("Open 'Projects Overview' page and check that the page is opened");
        projectsOverviewPage.open()
                            .isPageOpened();
    }
}




