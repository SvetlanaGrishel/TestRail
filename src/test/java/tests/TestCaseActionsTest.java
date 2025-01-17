package tests;

import dto.TestCase;
import io.qameta.allure.*;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import pages.AddTestCasePage;
import pages.TestCaseDetailsPage;

import static org.testng.Assert.assertEquals;

@Log4j2
public class TestCaseActionsTest extends BaseTest {

    public String projectWithTestCases = "Project with test cases";
    public String projectWithTwoTestCases = "Project with two test cases";

    TestCase testCase = TestCase.builder()
            .sectionTestCase("Test Cases")
            .templateTestCase("Test Case (Text)")
            .typeTestCase("Automated")
            .priorityTestCase("Medium")
            //.assignedToTestCase("None")
            .automationTypeTestCase("Ranorex")
            .preconditionsTestCase("This is test case preconditions")
            .stepsTestCase("1. Log in to the system. 2. Open 'Homepage'")
            .expectedResultTestCase("1. User is logged in. 2. 'Homepage' is opened")
            .build();

    TestCase testCase2 = TestCase.builder()
            .sectionTestCase("Test Cases")
            .templateTestCase("Test Case (Text)")
            .typeTestCase("Security")
            .priorityTestCase("Critical")
            //.assignedToTestCase("None")
            .automationTypeTestCase("None")
            .preconditionsTestCase("Test preconditions")
            .stepsTestCase("1. Log in to the system with valid credentials. 2. Check that username and password aren't sent" +
                    " in the URL")
            .expectedResultTestCase("1. User is logged in with valid credentials. 2. Username and password aren't sent" +
                    " in the URL")
            .build();

    TestCase testCase3 = TestCase.builder()
            .sectionTestCase("Test Cases")
            .templateTestCase("Test Case (Text)")
            .typeTestCase("Acceptance")
            .priorityTestCase("Low")
            //.assignedToTestCase("None")
            .automationTypeTestCase("Ranorex")
            .preconditionsTestCase("PRECONDITIIONS\nPRECONDITIIONS\nPRECONDITIIONS")
            .stepsTestCase("STEPS\nSTEPS\nSTEPS")
            .expectedResultTestCase("EXPECTED_RESULT\nEXPECTED_RESULT\nEXPECTED_RESULT")
            .build();

    //------------------------------------------------------------------------------------------------------------------
    //1 - ДОБАВЛЕНИЕ НОВОГО ПРОЕКТА С ТЕСТ-КЕЙСОМ
    @Test(testName = "Add test case to project", description = "Add test case to project", priority = 1)
    @Description("Add test case to project")
    @Epic("'Add Test Case' module TestRail")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Svetlana Grishel")
    public void checkCreatingTestCase() {
        loginStep.loginStep();
        addProjectPage.open()
                .isPageOpened()
                .fillProjectName(projectWithTestCases);
        addProjectPage.clickAddProjectButton();
        homepage.open()
                .isPageOpened();
        homepage.openProjectDetailsPage(projectWithTestCases);
        projectDetailsPage.clickAddTestCaseLink();
        addTestCasePage.fillAddTestCaseForm(testCase, "Log in and open Homepage");
        assertEquals(TestCaseDetailsPage.getMessageForAddedTestCase(),
                "Successfully added the new test case. Add another",
                "FAIL checkCreatingTestCase");
    }

    //------------------------------------------------------------------------------------------------------------------
    //2 - ДОБАВЛЕНИЕ ДВУХ КЕЙСОВ И ПРОВЕРКА ВАЛИДАЦИИ ДЛЯ ПОЛЯ TITLE
    @Test(testName = "Add two test cases and check validation for 'Title' field",
            description = "Add two test cases and check validation for 'Title' field", priority = 2)
    @Description("Add two test cases and check validation for 'Title' field")
    @Epic("'Add Test Case' module TestRail")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Svetlana Grishel")
    public void checkAddAnotherLinkAndValidationForRequiredFiled() {
        loginStep.loginStep();
        addProjectPage.open()
                .isPageOpened()
                .fillProjectName(projectWithTwoTestCases);
        addProjectPage.clickAddProjectButton();
        homepage.open()
                .isPageOpened()
                .openProjectDetailsPage(projectWithTwoTestCases);
        projectDetailsPage.clickAddTestCaseLink();
        addTestCasePage.fillAddTestCaseForm(testCase2, "Log in with valid credentials and check URL");
        assertEquals(TestCaseDetailsPage.getMessageForAddedTestCase(),
                "Successfully added the new test case. Add another",
                "FAIL checkCreatingProjectWithTwoCases");
        testCaseDetailsPage.clickAddAnotherLink();
        addTestCasePage.fillAddTestCaseForm(testCase3, "Just test data");
        assertEquals(AddTestCasePage.getMessageAboutRequiredField(),
                "Field Title is a required field.",
                "FAIL checkCreatingProjectWithTwoCases");
    }
}
