package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test(testName = "Log in to TestRail with valid credentials", description = "Log in to TestRail with valid " +
            "credentials", priority = 1)
    @Description("Log in to the TestRail with valid credentials")
    @Epic("'Login' module")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Svetlana Grishel")
    public void checkLoginWithValidCredentials() {
        loginStep.loginStep();
        assertEquals(
                homepage.getTitle(),
                "All Projects",
                "'All Projects' page isn't opened after login'");
    }

    @Test(testName = "Log in with empty password and valid email", description = "Log in with empty password and valid " +
            "email", priority = 2)
    @Description("Log in with empty password and valid email")
    @Epic("'Login' module")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Svetlana Grishel")
    public void checkLoginWithEmptyPassword() {
        loginPage.openLoginPage()
                .login(EMAIL, " ");
        assertEquals(loginPage.getLoginErrorMessageEmptyFields(),
                "Password is required.",
                "FAIL checkLoginWithEmptyPassword test");
    }

    @Test(testName = "Log in with empty email and valid password", description = "Log in with empty email and valid " +
            "password", priority = 3)
    @Description("Log in with empty email and valid password")
    @Epic("'Login' module")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Svetlana Grishel")
    public void checkLoginWithEmptyEmail() {
        loginPage.openLoginPage()
                .login("", PASSWORD);
        assertEquals(loginPage.getLoginErrorMessageEmptyFields(),
                "Email/Login is required.",
                "FAIL checkLoginWithEmptyEmail test");
    }

    @Test(testName = "Log in with not valid credentials", description = "Log in with not valid credentials: email and " +
            "password", priority = 4)
    @Description("Log in with not valid credentials: email and password")
    @Epic("'Login' module")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Svetlana Grishel")
    public void checkLoginWithNotValidCredentials() {
        loginPage.openLoginPage()
                .login(NOT_VALID_EMAIL, NOT_VALID_PASSWORD);
        assertEquals(loginPage.getGeneralLoginErrorMessage(),
                "Email/Login or Password is incorrect. Please try again.",
                "FAIL checkLoginWithNotValidCredentials");
    }
}
