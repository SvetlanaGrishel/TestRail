package tests;

import com.github.javafaker.Faker;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProjectActionsTest extends BaseTest {

    Faker faker = new Faker();

    private static final String projectNameFirst = "FIRST_project";
    private String projectNameSecond = faker.company().name();
    private String projectNameThird = faker.company().name();
    private static final String projectNameDeletedProject = "DELETE";
    private static final String editProjectName = "EDITED_name_";
    private static final String cancelDeleteProjectName = "Cancel deleting project";

    SoftAssert softAssert = new SoftAssert();

    @Test(testName = "Add 1st Project in TestRail", description = "Add the 1st Project in TestRail when projects " +
            "are missing", priority = 1)
    @Description("Add the 1st Project in TestRail when projects are missing")
    @Epic("'Add Project' module")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Svetlana Grishel")
    public void checkCreatingFirstProjectInTestrail() {
        loginStep.loginStep();
        addProjectPage.openAddProjectPage()
                .isAddProjectPageOpened()
                .fillProjectName(projectNameFirst)
                .clickAddProjectButton()
                .openProjectsOverviewPage()
                .isPageOpened();
        assertTrue(projectsOverviewPage.isProjectFound(projectNameFirst), "Project {projectNameFirst} not " +
                "found on 'Projects' page");
    }

    @Test(testName = "Add 2nd Project with name only", description = "Add 2nd Project with name only", priority = 2)
    @Description("Add 2nd project with name only")
    @Epic("'Add Project' module")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Svetlana Grishel")
    public void checkAddingProjectWithNameOnly() {
        loginStep.loginStep();
        addProjectPage.openAddProjectPage()
                .isAddProjectPageOpened()
                .fillProjectName(projectNameSecond)
                .clickAddProjectButton();
        softAssert.assertEquals(addProjectPage.getMessageForCreatedProject(),
                "Successfully added the new project.",
                "FAIL checkAddingProjectWithNameOnly test");
        softAssert.assertTrue(projectsOverviewPage.isProjectFound(projectNameSecond), "Project {projectNameFirst}" +
                " not found on 'Projects' page");
        softAssert.assertAll();
    }

    @Test(testName = "Add 3rd Project with name and variables", description = "Add 3rd Project with name and variables",
            priority = 3)
    @Description("Add 3rd Project with name and variables")
    @Epic("'Add Project' module")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Svetlana Grishel")
    public void checkAddingProjectWithVariables() {
        loginStep.loginStep();
        addProjectPage.openAddProjectPage()
                .isAddProjectPageOpened();
        variableStep.createVariable()
                .clickProjectTab()
                .fillProjectName(projectNameThird)
                .clickAddProjectButton();
        softAssert.assertEquals(addProjectPage.getMessageForCreatedProject(),
                "Successfully added the new project.",
                "FAIL checkAddingProjectWithVariables test");
        softAssert.assertTrue(projectsOverviewPage.isProjectFound(projectNameThird), "Project {projectNameSecond}" +
                " not found on 'Projects' page");
        softAssert.assertAll();
    }

    @Test(testName = "Edit Project name for the first project in the list", description = "Edit Project name for the first " +
            "project in the list", priority = 4)
    @Description("Edit Project name for the first project in the list")
    @Epic("'Edit Project' module")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Svetlana Grishel")
    public void checkEditProjectName() {
        loginStep.loginStep();
        projectsOverviewPage.openProjectsOverviewPage()
                .isPageOpened()
                .openEditProjectPage()
                .fillProjectName(editProjectName)
                .clickAddProjectButton();
        assertEquals(projectsOverviewPage.getMessageForUpdatedProject(),
                "Successfully updated the project.",
                "FAIL checkEditProjectName test");
    }

    @Test(testName = "Create new 4th project and cancel deleting of it", description = "Create new 4th project and cancel " +
            "deleting of it", priority = 5)
    @Description("Create new 4th project and cancel deleting of it")
    @Epic("'Delete Project' module")
    @Severity(SeverityLevel.TRIVIAL)
    @Owner("Svetlana Grishel")
    public void checkCancelingProjectDeleting() {
        loginStep.loginStep();
        projectsOverviewPage.openProjectsOverviewPage()
                .isPageOpened();
        addProjectPage.openAddProjectPage()
                .isAddProjectPageOpened()
                .fillProjectName(cancelDeleteProjectName)
                .clickAddProjectButton()
                .openProjectsOverviewPage()
                .isPageOpened()
                .clickIconToDeleteProject(cancelDeleteProjectName)
                .notConfirmProjectDeletion();
        assertTrue(projectsOverviewPage.isProjectFound(cancelDeleteProjectName), "Project {projectNameFirst} not " +
                "found on 'Projects' page");
    }

    @Test(testName = "Create new 5th project and delete it", description = "Create new 5th project and delete it",
            priority = 6)
    @Description("Create new 5th project and delete it")
    @Epic("'Delete Project' module")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Svetlana Grishel")
    public void checkProjectDeleting() {
        loginStep.loginStep();
        projectsOverviewPage.openProjectsOverviewPage()
                .isPageOpened();
        addProjectPage.openAddProjectPage()
                .isAddProjectPageOpened()
                .fillProjectName(projectNameDeletedProject)
                .clickAddProjectButton()
                .openProjectsOverviewPage()
                .isPageOpened()
                .clickIconToDeleteProject(projectNameDeletedProject)
                .isDeleteProjectModalOpened()
                .confirmProjectDeletion();
        assertEquals(projectsOverviewPage.getMessageForDeletedProject(),
                "Successfully deleted the project.",
                "FAIL checkProjectDeleting");
    }
}
