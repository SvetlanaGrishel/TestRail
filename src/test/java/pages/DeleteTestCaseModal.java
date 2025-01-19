package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class DeleteTestCaseModal extends BasePage {

    private static final By DELETE_TEST_CASE_MODAL_TITLE = By.id("ui-dialog-title-casesDeletionDialog");
    private static final By MARK_AS_DELETED_BUTTON_DELETE_TEST_CASE_MODAL = By.xpath("//*[@id='casesDeletionDialog']" +
            "/div[3]/div/a[1]");
    private static final By DELETE_PERMANENTLY_BUTTON_DELETE_TEST_CASE_MODAL = By.xpath("//*[@id='casesDeletionDialog']" +
            "/div[3]/div/a[2]");
    private static final By FINAL_DELETE_PERMANENTLY_BUTTON_DELETE_TEST_CASE_MODAL = By.xpath("//*[@id='casesDeletionConfirmationDialog']/div[3]/div/a[1]");
    private static final By CANCEL_BUTTON_DELETE_TEST_CASE_MODAL = By.xpath("//*[@id='casesDeletionDialog']" +
            "/div[3]/div/a[3]");

    public DeleteTestCaseModal(WebDriver driver) {
        super(driver);
    }

    @Step("Confirm permanent Test Case deletion on 'Confirmation' modal")
    public void confirmPermanentTestCaseDeletion() {
        log.info("Confirm permanent Test Case deletion on 'Confirmation' modal");
        driver.findElement(DELETE_PERMANENTLY_BUTTON_DELETE_TEST_CASE_MODAL).click();
    }

    @Step("Final confirm permanent Test Case deletion on 'Confirmation' modal")
    public TestCasesOverviewPage finalConfirmPermanentTestCaseDeletion() {
        log.info("Final confirm permanent Test Case deletion on 'Confirmation' modal");
        driver.findElement(FINAL_DELETE_PERMANENTLY_BUTTON_DELETE_TEST_CASE_MODAL).click();
        return new TestCasesOverviewPage(driver);
    }

    @Step("Cancel Test Case deletion on 'Confirmation' modal")
    public void cancelTestCaseDeletion() {
        log.info("Cancel Test Case deletion on 'Confirmation' modal");
        driver.findElement(CANCEL_BUTTON_DELETE_TEST_CASE_MODAL).click();
    }

    @Step("Mark Test Case as deleted on 'Confirmation' modal")
    public void markTestCaseAsDeleted() {
        log.info("Mark Test Case as deleted on 'Confirmation' modal");
        driver.findElement(MARK_AS_DELETED_BUTTON_DELETE_TEST_CASE_MODAL).click();
    }

    @Override
    @Step("Open 'Confirmation' modal for test case deletion")
    public DeleteTestCaseModal open() {
        return null;
    }

    @Override
    @Step("Check that 'Confirmation' modal for test case deletion is opened")
    public DeleteTestCaseModal isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(DELETE_TEST_CASE_MODAL_TITLE));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("'Add Project' page isn't opened");
        }
        return this;
    }
}
