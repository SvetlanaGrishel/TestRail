package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class LoginPage extends BasePage {

    private static final By EMAIL_INPUT = By.id("name");
    private static final By PASSWORD_INPUT = By.id("password");
    private static final By LOGIN_BUTTON = By.id("button_primary");
    private static final String BASE_TESTRAIL_URL = "https://sgtestrail.testrail.io/index.php?/auth/login/";
    private final By EMPTY_FIELDS_LOGIN_ERROR_MESSAGE = By.xpath("//div[contains(@class, 'loginpage-message')]");
    private final By GENERAL_LOGIN_ERROR_MESSAGE = By.xpath("//div[contains(@class, 'error-text')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Log in to the system with valid credentials: {email} and {password}")
    public Homepage login(String email, String password) {
        log.info("Log in using credentials '{}' and '{}'", email, password);
        driver.findElement(EMAIL_INPUT).sendKeys(email);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return new Homepage(driver);
    }

    @Step("Get the texts of error messages on 'Login' page when one field is empty")
    public String getLoginErrorMessageEmptyFields() {
        log.info("Get the texts of error messages on Login page when one field is empty");
        return driver.findElement(EMPTY_FIELDS_LOGIN_ERROR_MESSAGE).getText();
    }

    @Step("Get the texts of error messages on 'Login' page when one field is empty")
    public String getGeneralLoginErrorMessage() {
        log.info("Get the text of general error message on Login page");
        return driver.findElement(GENERAL_LOGIN_ERROR_MESSAGE).getText();
    }

    @Step("Open 'Login page'")
    public LoginPage openLoginPage() {
        log.info("Open 'Login' page in TestRail");
        driver.get(BASE_TESTRAIL_URL);
        return this;
    }

    @Step("Check that 'Login' page is opened")
    public LoginPage isLoginPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("'Login' page isn't opened");
        }
        return this;
    }
}
