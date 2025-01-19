package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.ConfigureVariablePage;
import pages.Homepage;

@Log4j2
public class VariableStep {

    Homepage homepage;
    ConfigureVariablePage configureVariablePage;

    public VariableStep(WebDriver driver) {
        homepage = new Homepage(driver);
        configureVariablePage = new ConfigureVariablePage(driver);
    }

    @Step("Fill information to create new variable and click 'OK' button")
    public void createVariable() {
        log.info("Fill information to create new variable and click 'OK' button");
        configureVariablePage.clickTabVariables();
        configureVariablePage.fillVariableInformation();
        configureVariablePage.clickOkButton();
    }
}
