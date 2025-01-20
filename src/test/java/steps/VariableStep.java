package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.AddProjectPage;
import pages.ConfigureVariablePage;
import pages.Homepage;

import static pages.BasePage.driver;

@Log4j2
public class VariableStep {

    Homepage homepage;
    ConfigureVariablePage configureVariablePage;

    public VariableStep(WebDriver driver) {
        homepage = new Homepage(driver);
        configureVariablePage = new ConfigureVariablePage(driver);
    }

    @Step("Fill information to create new variable and click 'OK' button")
    public AddProjectPage createVariable() {
        log.info("Fill information to create new variable and click 'OK' button");
        configureVariablePage.clickTabVariables()
                .isConfigureVariablePageOpened()
                .fillVariableInformation()
                .clickOkButton();
        return new AddProjectPage(driver);
    }
}
