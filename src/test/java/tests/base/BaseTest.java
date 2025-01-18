package tests.base;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.*;
import steps.LoginStep;
import steps.OpenProjectsOverviewStep;
import steps.VariableStep;
import utils.PropertyReader;

import java.time.Duration;

@Listeners(TestListener.class)
public class BaseTest {

    protected WebDriver driver;
    protected Homepage homepage;
    protected LoginPage loginPage;
    protected ConfigureVariablePage configureVariablePage;
    protected AddProjectPage addProjectPage;
    protected ProjectsOverviewPage projectsOverviewPage;
    protected LoginStep loginStep;
    protected VariableStep variableStep;
    protected DeleteProjectModal deleteProjectModal;
    protected ProjectDetailsPage projectDetailsPage;
    protected AddTestCasePage addTestCasePage;
    protected TestCaseDetailsPage testCaseDetailsPage;
    protected OpenProjectsOverviewStep openProjectsOverviewStep;

    //ПЕРЕПИСАТЬ И СКРЫТЬ (!!!)
    protected String EMAIL = System.getProperty("EMAIL", PropertyReader.getProperty("EMAIL"));
    protected String PASSWORD = System.getProperty("PASSWORD", PropertyReader.getProperty("PASSWORD"));
    protected String NOT_VALID_EMAIL = System.getProperty("NOT_VALID_EMAIL", PropertyReader.getProperty("NOT_VALID_EMAIL"));
    protected String NOT_VALID_PASSWORD = System.getProperty("NOT_VALID_PASSWORD", PropertyReader.getProperty("NOT_VALID_PASSWORD"));

    @Parameters({"browser"})
    @BeforeMethod
    @Step("Open browser {browser}")
    public void setup(@Optional("chrome") String browser, ITestContext context) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            //options.addArguments("--headless");
            options.addArguments("--disable-notifications");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("start-maximized");
            edgeOptions.addArguments("--headless");
            driver = new EdgeDriver(edgeOptions);
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("start-maximized");
            firefoxOptions.addArguments("--headless");
            driver = new FirefoxDriver(firefoxOptions);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        homepage = new Homepage(driver);
        loginPage = new LoginPage(driver);
        loginStep = new LoginStep(driver);
        addProjectPage = new AddProjectPage(driver);
        variableStep = new VariableStep(driver);
        configureVariablePage = new ConfigureVariablePage(driver);
        projectsOverviewPage = new ProjectsOverviewPage(driver);
        deleteProjectModal = new DeleteProjectModal(driver);
        projectDetailsPage = new ProjectDetailsPage(driver);
        addTestCasePage = new AddTestCasePage(driver);
        testCaseDetailsPage = new TestCaseDetailsPage(driver);
        openProjectsOverviewStep = new OpenProjectsOverviewStep(driver);
    }
//
//    @Step("Close browser")
//    @AfterMethod(alwaysRun = true)
//    public void tearDown(ITestResult result) {
//        if (ITestResult.FAILURE == result.getStatus()) {
//            takeScreenshot(driver);
//        }
//        driver.quit();
//    }
}
