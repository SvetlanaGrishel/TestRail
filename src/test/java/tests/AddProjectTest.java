package tests;

import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Log4j2
public class AddProjectTest extends BaseTest {

    Faker faker = new Faker();
    String projectNameFirst = faker.company().name();
    String projectNameSecond = faker.company().name();

    SoftAssert softAssert = new SoftAssert();

    @Test(testName = "Add new project with name only", description = "Add new project with name only")
    @Description("Add new project with name only")
    @Epic("'Add Project' module TestRail")
    @Severity(SeverityLevel.CRITICAL)
    public void addProjectWithNameOnly() {
        loginPage.open()
                .login(EMAIL, PASSWORD);
        addProjectPage.open()
                .isPageOpened()
                .fillProjectName(projectNameFirst);
        addProjectPage.clickAddProjectButton();
        softAssert.assertEquals(addProjectPage.getMessageForCreatedProject(),
                "Successfully added the new project.",
                "FAIL checkHomepageElements");
        softAssert.assertTrue(projectsPage.isProjectFound(projectNameFirst), "Project {projectNameFirst} not " +
                "found on 'Projects' page");
        softAssert.assertAll();
    }

    @Test(testName = "Add new project with name and variables", description = "Add new project with name and variables")
    @Description("Add new project with name and variables")
    @Epic("'Add Project' module TestRail")
    @Severity(SeverityLevel.NORMAL)
    public void addProjectWithVariables() {
        loginPage.open()
                .login(EMAIL, PASSWORD);
        addProjectPage.open()
                .isPageOpened();
        variableStep.createVariable();
        addProjectPage.clickProjectTab();
        addProjectPage.fillProjectName(projectNameSecond);
        addProjectPage.clickAddProjectButton();
        softAssert.assertEquals(addProjectPage.getMessageForCreatedProject(),
                "Successfully added the new project.",
                "FAIL addProjectWithVariables");
        softAssert.assertTrue(projectsPage.isProjectFound(projectNameSecond), "Project {projectNameSecond} not " +
                "found on 'Projects' page");
        softAssert.assertAll();
    }


}
