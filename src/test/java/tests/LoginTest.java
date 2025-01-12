package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;

@Log4j2
public class LoginTest extends BaseTest{

    @Test(testName = "Log in with valid credentials", description = "Log in in TestRails with valid credentials")
    @Description("Log in with valid credentials in TestRail")
    @Epic("Login module TestRail")
    @Severity(SeverityLevel.CRITICAL)
    public void loginWithValidCredentials() {
        loginPage.open();
        loginPage.login(email, password);
        assertEquals(
                allProjectsPage.getTitle(),
                "All Projects",
                "Переход на страницу не выполнен");
    }

    //ПЕРЕПИСАТЬ С DATA PROVIDER (!!!)
    @Test(testName = "Log in with empty password", description = "Log in with empty password")
    @Description("Log in with empty password in TestRail")
    @Epic("Login module TestRail")
    @Severity(SeverityLevel.NORMAL)
    public void loginWithEmptyPassword() {
        loginPage.open();
        loginPage.login(email, " ");
        assertEquals(loginPage.getLoginErrorMessageEmptyFields(),
                "Password is required.",
                "FAIL loginWithEmptyPassword");
    }

    @Test(testName = "Log in with empty email", description = "Log in with empty email")
    @Description("Log in with empty email in TestRail")
    @Epic("Login module TestRail")
    @Severity(SeverityLevel.NORMAL)
    public void loginWithEmptyEmail() {
        loginPage.open();
        loginPage.login("", password);
        assertEquals(loginPage.getLoginErrorMessageEmptyFields(),
                "Email/Login is required.",
                "FAIL loginWithEmptyEmail");
    }

    @Test(testName = "Log in with not valid credentials", description = "Log in with not valid credentials")
    @Description("Log in with not valid credentials in TestRail")
    @Epic("Login module TestRail")
    @Severity(SeverityLevel.NORMAL)
    public void loginWithNotValidCredentials() {
        loginPage.open();
        loginPage.login(notValidEmail, notValidPassword);
        assertEquals(loginPage.getGeneralLoginErrorMessage(),
                "Email/Login or Password is incorrect. Please try again.",
                "FAIL loginWithNotValidCredentials");
    }

    //ПЕРЕПИСАТЬ
    @Test(testName = "Log in with empty email and password", description = "Log in with empty email and password")
    @Description("Log in with empty email and password in TestRail")
    @Epic("Login module TestRail")
    @Severity(SeverityLevel.NORMAL)
    public void loginWithEmptyFields() {
        loginPage.open();
        loginPage.login("", "");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(loginPage.getLoginErrorMessageEmptyFields(),
                "Email/Login is required.",
                "FAIL loginWithEmptyFields");
        softAssert.assertEquals(loginPage.getLoginErrorMessageEmptyFields(),
                "Password is required.",
                "FAIL loginWithEmptyFields");
        softAssert.assertAll();

    }





}
