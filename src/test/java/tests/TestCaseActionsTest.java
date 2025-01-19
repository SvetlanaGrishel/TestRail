package tests;

import dto.TestCase;
import io.qameta.allure.*;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import pages.AddTestCasePage;
import pages.TestCaseDetailsPage;
import tests.base.BaseTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class TestCaseActionsTest extends BaseTest {

    private static final String projectWithTestCases = "Project with test cases";
    private static final String projectWithTestCaseToDelete = "Project with test case to delete";
    private static final String projectWithTwoTestCases = "Project with two test cases";
    private static final String editTestCaseName = "EDITED_test_case_name_";

    TestCase testCase = TestCase.builder()
            .sectionTestCase("Test Cases")
            .templateTestCase("Test Case (Text)")
            .typeTestCase("Automated")
            .priorityTestCase("Medium")
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
            .automationTypeTestCase("Ranorex")
            .preconditionsTestCase("PRECONDITIIONS\nPRECONDITIIONS\nPRECONDITIIONS")
            .stepsTestCase("STEPS\nSTEPS\nSTEPS")
            .expectedResultTestCase("EXPECTED_RESULT\nEXPECTED_RESULT\nEXPECTED_RESULT")
            .build();

    @Test(testName = "Add new Project with Test Case", description = "Add new Project with Test Case", priority = 1)
    @Description("Add new Project with Test Case")
    @Epic("'Add Test Case' module")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Svetlana Grishel")
    public void checkCreatingProjectWithTestCase() {
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
                "FAIL checkCreatingProjectWithTestCase test");
    }

    @Test(testName = "Add Project with two Test Cases and check validation for 'Title' field",
            description = "Add Project with two Test Cases and check validation for 'Title' field", priority = 2)
    @Description("Add Project with two Test Cases and check validation for 'Title' field")
    @Epic("'Add Test Case' module")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Svetlana Grishel")
    public void checkAddAnotherLinkAndValidationForRequiredField() {
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
                "FAIL checkAddAnotherLinkAndValidationForRequiredField test");
        testCaseDetailsPage.clickAddAnotherLink();
        addTestCasePage.fillAddTestCaseForm(testCase3, "");
        assertEquals(AddTestCasePage.getMessageAboutRequiredField(),
                "Field Title is a required field.",
                "FAIL checkAddAnotherLinkAndValidationForRequiredField test");
    }

    @Test(testName = "Edit Test Case title in the already created Project", description = "Edit Test Case title in the " +
            "already created Project", priority = 3)
    @Description("Edit Test Case title in the already created Project")
    @Epic("'Edit Test Case' module")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Svetlana Grishel")
    public void checkEditingTitleTestCase() {
        loginStep.loginStep();
        homepage.open()
                .isPageOpened();
        homepage.openProjectDetailsPage(projectWithTestCases);
        projectDetailsPage.clickViewAllTestCasesLink();
        testCasesOverviewPage.openTestCaseDetailsPage();
        testCaseDetailsPage.clickEditIcon();
        addTestCasePage.fillTestCaseTitle(editTestCaseName);
        addTestCasePage.clickSaveTestCase();
        assertEquals(TestCaseDetailsPage.getMessageForUpdatedTestCase(),
                "Successfully updated the test case.",
                "FAIL checkEditingTitleTestCase");
    }

    @Test(testName = "Add Project with Test Case and delete Test Case", description = "Add Project with Test Case and " +
            "delete Test Case test case", priority = 4)
    @Description("Add Project with Test Case and delete Test Case")
    @Epic("'Delete Test Case' module")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Svetlana Grishel")
    public void checkCreatingProjectWithTestCaseAndDeleteTestCase() {
        loginStep.loginStep();
        addProjectPage.open()
                .isPageOpened()
                .fillProjectName(projectWithTestCaseToDelete);
        addProjectPage.clickAddProjectButton();
        homepage.open()
                .isPageOpened();
        homepage.openProjectDetailsPage(projectWithTestCaseToDelete);
        projectDetailsPage.clickAddTestCaseLink();
        addTestCasePage.fillAddTestCaseForm(testCase3, "Test Case to delete");
        testCaseDetailsPage.clickTestCasesBreadcrumbs();
        testCasesOverviewPage.markCheckboxToSelectAllTestCases();
        testCasesOverviewPage.clickDeleteButtonForTestCases();
        deleteTestCaseModal.confirmPermanentTestCaseDeletion();
        deleteTestCaseModal.finalConfirmPermanentTestCaseDeletion();
        assertTrue(testCasesOverviewPage.checkIconTestCasesQuantity(), "Quantity of Test Cases is not equal to 0," +
                "FAIL checkCreatingProjectWithTestCaseAndDeleteTestCase test");
    }
}
