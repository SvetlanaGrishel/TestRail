package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class AllProjectsPage extends BasePage {

    private final By TITLE_ALL_PROJECTS_PAGE = By.xpath("//*[@id='content-header']/div/div[2]");

    public AllProjectsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get title of the 'All Projects' page")
    public String getTitle() {
        log.info("Get title of the 'All Projects' page");
        //Добавляем Timeout Exception
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE_ALL_PROJECTS_PAGE));
        return driver.findElement(TITLE_ALL_PROJECTS_PAGE).getText();
    }

    @Override
    public AllProjectsPage isPageOpened() {
        return null;
    }

    @Override
    public AllProjectsPage open() {
        return null;
    }


}
