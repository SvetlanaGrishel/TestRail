package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.Homepage;
import pages.ConfigureVariablePage;

@Log4j2
public class VariableStep {

    Homepage homepage;
    ConfigureVariablePage configureVariablePage;

    public VariableStep(WebDriver driver) {
        homepage = new Homepage(driver);
        configureVariablePage = new ConfigureVariablePage(driver);
    }

    @Step("Create account with Account information, Address Information and Additional Information")
    public void createVariable() {
        log.info("Creating new account, filling information");
        configureVariablePage.clickTabVariables();
        configureVariablePage.fillVariableInformation();
        configureVariablePage.clickOkButton();
    }


}
