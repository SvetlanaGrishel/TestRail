package tests;

import io.qameta.allure.*;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;

@Log4j2
public class HomepageTest extends BaseTest {

    SoftAssert softAssert = new SoftAssert();

    @Test(testName = "Check elements on Homepage", description = "Check elements on Homepage")
    @Description("Check elements on Homepage")
    @Epic("'Homepage' module TestRail")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Svetlana Grishel")
    public void checkHomepageElements() {
        loginPage.open()
                .login(EMAIL, PASSWORD);
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
