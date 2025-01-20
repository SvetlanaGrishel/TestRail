package steps;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.Homepage;
import pages.LoginPage;
import utils.PropertyReader;

import static pages.BasePage.driver;

@Log4j2
public class LoginStep {

    LoginPage loginPage;
    Homepage homepage;

    private String EMAIL = System.getProperty("EMAIL", PropertyReader.getProperty("EMAIL"));
    private String PASSWORD = System.getProperty("PASSWORD", PropertyReader.getProperty("PASSWORD"));

    public LoginStep(WebDriver driver) {
        loginPage = new LoginPage(driver);
    }

    public Homepage loginStep() {
        log.info("Login to the 'TestRail' with valid credentials");
        loginPage.openLoginPage()
                .isLoginPageOpened()
                .login(EMAIL, PASSWORD);
        return new Homepage(driver);
    }
}
