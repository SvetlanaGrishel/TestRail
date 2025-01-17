package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Log4j2
public class LoginTest extends BaseTest {

    @Test(testName = "Log in with valid credentials", description = "Log in in TestRails with valid credentials", priority = 1)
    @Description("Log in with valid credentials in TestRail")
    @Epic("Login module TestRail")
    @Severity(SeverityLevel.CRITICAL)
    public void loginWithValidCredentials() {
        loginStep.loginStep();
        assertEquals(
                homepage.getTitle(),
                "All Projects",
                "Переход на страницу не выполнен");
    }

    //ПЕРЕПИСАТЬ С DATA PROVIDER (!!!) + СКРЫТЬ ДАННЫЕ
    @Test(testName = "Log in with empty password", description = "Log in with empty password", priority = 2)
    @Description("Log in with empty password in TestRail")
    @Epic("Login module TestRail")
    @Severity(SeverityLevel.NORMAL)
    public void loginWithEmptyPassword() {
        loginPage.open();
        loginPage.login(EMAIL, " ");
        assertEquals(loginPage.getLoginErrorMessageEmptyFields(),
                "Password is required.",
                "FAIL loginWithEmptyPassword");
    }

    //ПЕРЕПИСАТЬ С DATA PROVIDER (!!!) + СКРЫТЬ ДАННЫЕ
    @Test(testName = "Log in with empty email", description = "Log in with empty email", priority = 3)
    @Description("Log in with empty email in TestRail")
    @Epic("Login module TestRail")
    @Severity(SeverityLevel.NORMAL)
    public void loginWithEmptyEmail() {
        loginPage.open();
        loginPage.login("", PASSWORD);
        assertEquals(loginPage.getLoginErrorMessageEmptyFields(),
                "Email/Login is required.",
                "FAIL loginWithEmptyEmail");
    }

    //ПЕРЕПИСАТЬ С DATA PROVIDER (!!!) + СКРЫТЬ ДАННЫЕ
    @Test(testName = "Log in with not valid credentials", description = "Log in with not valid credentials", priority = 4)
    @Description("Log in with not valid credentials in TestRail")
    @Epic("Login module TestRail")
    @Severity(SeverityLevel.NORMAL)
    public void loginWithNotValidCredentials() {
        loginPage.open();
        loginPage.login(NOT_VALID_EMAIL, NOT_VALID_PASSWORD);
        assertEquals(loginPage.getGeneralLoginErrorMessage(),
                "Email/Login or Password is incorrect. Please try again.",
                "FAIL loginWithNotValidCredentials");
    }
}
