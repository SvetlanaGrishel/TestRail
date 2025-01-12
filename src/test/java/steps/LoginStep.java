package steps;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.Homepage;
import pages.LoginPage;
import utils.PropertyReader;

@Log4j2
public class LoginStep {

    LoginPage loginPage;
    Homepage homepage;

    private String EMAIL = System.getProperty("EMAIL", PropertyReader.getProperty("EMAIL"));
    private String PASSWORD = System.getProperty("PASSWORD", PropertyReader.getProperty("PASSWORD"));

    public LoginStep(WebDriver driver) {
        loginPage = new LoginPage(driver);
    }

    public void login() {
        log.info("Login to the 'TestRail' with valid credentials");
        loginPage.open()
                .isPageOpened()
                .login(EMAIL, PASSWORD);
    }
}
