package tests;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.*;
import steps.LoginStep;
import steps.VariableStep;

import java.time.Duration;

import static utils.AllureUtils.takeScreenshot;

@Listeners(TestListener.class)
public class BaseTest {

    protected WebDriver driver;
    protected Homepage homepage;
    protected LoginPage loginPage;
    protected ConfigureVariablePage configureVariablePage;
    protected AddProjectPage addProjectPage;
    protected ProjectsPage projectsPage;
    protected LoginStep loginStep;
    protected VariableStep variableStep;

    //ПЕРЕПИСАТЬ И СКРЫТЬ (!!!)
    String email = "HIDDEN";
    String password = "HIDDEN";
    String notValidEmail = "test@gmail.com";
    String notValidPassword = "111222";

    @Parameters({"browser"})
    @BeforeMethod
    @Step("Open browser {browser}")
    public void setup(@Optional("chrome") String browser, ITestContext context) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            //options.addArguments("--headless");
            //options.addArguments("--disable-notifications");
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
        projectsPage = new ProjectsPage(driver);
    }

    @Step("Close browser")
    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            takeScreenshot(driver);
        }
        driver.quit();
    }
}
