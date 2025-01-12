package steps;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import utils.PropertyReader;

@Log4j2
public class LoginStep {

    LoginPage loginPage;

    private String USERNAME = System.getProperty("USERNAME", PropertyReader.getProperty("USERNAME"));
    private String PASSWORD = System.getProperty("PASSWORD", PropertyReader.getProperty("PASSWORD"));

    public LoginStep(WebDriver driver) {
        loginPage = new LoginPage(driver);
    }

    public void login() {
        log.info("Login to the 'Salesforce' system with credentials");
        loginPage.open()
                .isPageOpened()
                .login(USERNAME, PASSWORD);
                //.open()
                //.isPageOpened();
    }
}
