package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class DeleteProjectModal extends BasePage{

    private static final By DELETE_PROJECT_MODAL_TITLE = By.id("ui-dialog-title-deleteDialog");
    private static final By CHECKBOX_DELETE_PROJECT_MODAL = By.xpath("//*[@id='deleteDialog']" +
            "/div[2]/div/div/label/input");
    private static final By YES_BUTTON_DELETE_PROJECT_MODAL = By.xpath("//*[@id='deleteDialog']/div[3]/a[1]");
    private static final By CANCEL_BUTTON_DELETE_PROJECT_MODAL = By.xpath("//*[@id='deleteDialog']/div[3]/a[3]");

    public DeleteProjectModal(WebDriver driver) {
        super(driver);
    }

    @Step("Confirm project deletion on 'Confirmation' modal")
    public void confirmProjectDeletion() {
        log.info("Confirm project deletion on 'Confirmation' modal");
        driver.findElement(CHECKBOX_DELETE_PROJECT_MODAL).click();
        driver.findElement(YES_BUTTON_DELETE_PROJECT_MODAL).click();
    }

    @Step("Close 'Confirmation' modal for project deleting")
    public void notConfirmProjectDeletion() {
        log.info("Close 'Confirmation' modal for project deleting");
        driver.findElement(CHECKBOX_DELETE_PROJECT_MODAL).click();
        driver.findElement(CANCEL_BUTTON_DELETE_PROJECT_MODAL).click();
    }

    @Override
    public BasePage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(DELETE_PROJECT_MODAL_TITLE));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("'Add Project' page isn't opened");
        }
        return this;
    }

    @Override
    public BasePage open() {
        return null;
    }
}
