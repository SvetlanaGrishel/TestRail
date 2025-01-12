package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Log4j2
public class HomepageTest extends BaseTest {

    SoftAssert softAssert = new SoftAssert();

    @Test(testName = "Check elements on Homepage", description = "Check elements on Homepage")
    @Description("Check elements on Homepage")
    @Epic("'Homepage' module TestRail")
    @Severity(SeverityLevel.NORMAL)
    public void checkHomepageElements() {
        loginPage.open()
                .login(email, password);
        homepage.isPageOpened();
        homepage.findAdministrationLink();
        softAssert.assertEquals(homepage.getTitle(),
                "All Projects",
                "Title of the page isn't 'All projects'");
        softAssert.assertTrue(homepage.findAdministrationLink(), "'Administration' link not found");
        softAssert.assertTrue(homepage.findAddProjectButton(), "'Add Project' button not found");
        softAssert.assertAll();
    }

}
