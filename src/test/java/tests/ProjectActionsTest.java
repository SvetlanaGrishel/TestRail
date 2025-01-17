package tests;

import com.github.javafaker.Faker;
import io.qameta.allure.*;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class ProjectActionsTest extends BaseTest {

    Faker faker = new Faker();
    public String projectNameFirst = "FIRST_project";
    public String projectNameSecond = faker.company().name();
    public String projectNameThird = faker.company().name();
    public String projectNameDeletedProject = "DELETE";
    public String editProjectName = "EDITED_name_";
    public String cancelDeleteProjectName = "Cancel deleting project";

    SoftAssert softAssert = new SoftAssert();

    //------------------------------------------------------------------------------------------------------------------
    //1 - ДОБАВЛЕНИЕ САМОГО ПЕРВОГО ПРОЕКТА
    @Test(testName = "Add the first project in TestRail", description = "Add the first project in TestRail when projects " +
            "are missing", priority = 1)
    @Description("Add the first project in TestRail when projects are missing")
    @Epic("'Add Project' module TestRail")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Svetlana Grishel")
    public void checkCreatingFirstProjectInTestrail() {
        loginStep.loginStep();
        addProjectPage.open()
                .isPageOpened()
                .fillProjectName(projectNameFirst);
        addProjectPage.clickAddProjectButton();
        projectsOverviewPage.open()
                .isPageOpened();
        assertTrue(projectsOverviewPage.isProjectFound(projectNameFirst), "Project {projectNameFirst} not " +
                "found on 'Projects' page");
    }

    //------------------------------------------------------------------------------------------------------------------
    //2 - ДОБАВЛЕНИЕ ПРОЕКТА ТОЛЬКО С НАЗВАНИЕМ
    @Test(testName = "Add new project with name only", description = "Add new project with name only", priority = 2)
    @Description("Add new project with name only")
    @Epic("'Add Project' module TestRail")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Svetlana Grishel")
    public void checkAddingProjectWithNameOnly() {
        loginStep.loginStep();
        addProjectPage.open()
                .isPageOpened()
                .fillProjectName(projectNameSecond);
        addProjectPage.clickAddProjectButton();
        softAssert.assertEquals(addProjectPage.getMessageForCreatedProject(),
                "Successfully added the new project.",
                "FAIL checkHomepageElements");
        softAssert.assertTrue(projectsOverviewPage.isProjectFound(projectNameSecond), "Project {projectNameFirst} not " +
                "found on 'Projects' page");
        softAssert.assertAll();
    }

    //------------------------------------------------------------------------------------------------------------------
    //3 - ДОБАВЛЕНИЕ ПРОЕКТА С ПЕРЕМЕННЫМИ
    @Test(testName = "Add new project with name and variables", description = "Add new project with name and variables", priority = 3)
    @Description("Add new project with name and variables")
    @Epic("'Add Project' module TestRail")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Svetlana Grishel")
    public void checkAddingProjectWithVariables() {
        loginStep.loginStep();
        addProjectPage.open()
                .isPageOpened();
        variableStep.createVariable();
        addProjectPage.clickProjectTab();
        addProjectPage.fillProjectName(projectNameThird);
        addProjectPage.clickAddProjectButton();
        softAssert.assertEquals(addProjectPage.getMessageForCreatedProject(),
                "Successfully added the new project.",
                "FAIL addProjectWithVariables");
        softAssert.assertTrue(projectsOverviewPage.isProjectFound(projectNameThird), "Project {projectNameSecond} not " +
                "found on 'Projects' page");
        softAssert.assertAll();
    }

    //------------------------------------------------------------------------------------------------------------------
    //4 - РЕДАКТИРОВАНИЕ НАЗВАНИЯ ПРОЕКТА
    @Test(testName = "Edit project", description = "Edit project", priority = 4)
    @Description("Edit project")
    @Epic("'Edit Project' module TestRail")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Svetlana Grishel")
    public void checkEditProjectName() {
        loginStep.loginStep();
        projectsOverviewPage.open()
                .isPageOpened()
                .openEditProjectPage();
        addProjectPage.fillProjectName(editProjectName);
        addProjectPage.clickAddProjectButton();
        assertEquals(projectsOverviewPage.getMessageForUpdatedProject(),
                "Successfully updated the project.",
                "FAIL editProjectName");
    }

    //------------------------------------------------------------------------------------------------------------------
    //5 - ОТМЕНА УДАЛЕНИЯ ПРОЕКТА
    @Test(testName = "Cancel deleting a project", description = "Cancel deleting a project", priority = 5)
    @Description("Cancel deleting a project")
    @Epic("'Delete Project' module TestRail")
    @Severity(SeverityLevel.TRIVIAL)
    @Owner("Svetlana Grishel")
    public void checkCancelingProjectDeleting() {
        loginStep.loginStep();
        projectsOverviewPage.open()
                .isPageOpened();
        addProjectPage.open()
                .isPageOpened()
                .fillProjectName(cancelDeleteProjectName);
        addProjectPage.clickAddProjectButton();
        projectsOverviewPage.open()
                .isPageOpened()
                .clickIconToDeleteProject(cancelDeleteProjectName);
        deleteProjectModal.notConfirmProjectDeletion();
        assertTrue(projectsOverviewPage.isProjectFound(cancelDeleteProjectName), "Project {projectNameFirst} not " +
                "found on 'Projects' page");
    }

    //------------------------------------------------------------------------------------------------------------------
    //6 - УДАЛЕНИЕ ПРОЕКТА С ПОДТВЕРЖДЕНИЕМ
    @Test(testName = "Delete a project", description = "Delete a project", priority = 6)
    @Description("Delete a project")
    @Epic("'Delete Project' module TestRail")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Svetlana Grishel")
    public void checkProjectDeleting() {
        loginStep.loginStep();
        projectsOverviewPage.open()
                .isPageOpened();
        addProjectPage.open()
                .isPageOpened()
                .fillProjectName(projectNameDeletedProject);
        addProjectPage.clickAddProjectButton();
        projectsOverviewPage.open()
                .isPageOpened()
                .clickIconToDeleteProject(projectNameDeletedProject);
        deleteProjectModal.confirmProjectDeletion();
        assertEquals(projectsOverviewPage.getMessageForDeletedProject(),
                "Successfully deleted the project.",
                "FAIL editProjectName");
    }
}
