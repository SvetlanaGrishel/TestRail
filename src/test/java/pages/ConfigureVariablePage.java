package pages;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class ConfigureVariablePage extends BasePage {

    Faker faker = new Faker();

    private static final By OK_BUTTON = By.id("userFieldSubmit");
    private static final By ADD_USER_VARIABLE_LINK = By.id("addConfig");
    private static final By TITLE_CONFIGURE_VARIABLE = By.id("ui-dialog-title-userFieldDialog");

    @Step("Fill variable information")
    public ConfigureVariablePage fillVariableInformation() {
        //log.info("Fill Account Information on 'New Account' modal '{}'", variable.getLabelVariable());
        driver.findElement(By.id("userFieldLabel")).sendKeys(faker.company().name());
        driver.findElement(By.id("userFieldDesc")).sendKeys(faker.company().url());
        driver.findElement(By.id("userFieldName")).sendKeys(faker.lorem().word());
        driver.findElement(By.id("userFieldFallback")).sendKeys(faker.dune().quote());
        driver.findElement(By.id("userFieldType")).sendKeys("String");

//ДОБАВИТЬ ОБЕРТКИ
//        new Input(driver, "Label").write(variable.getLabelVariable());
//        new Input(driver, "Description").write(variable.getDescriptionVariable());
//        new Input(driver, "System Name").write(variable.getSystemNameVariable());
//        new Picklist(driver, "String").select(variable.getTypeVariable());
        return this;
    }

    public ConfigureVariablePage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Open 'Configure Variable' modal")
    public ConfigureVariablePage open() {
        log.info("Open 'Configure Variable' modal");
        driver.findElement(ADD_USER_VARIABLE_LINK).click();
        return this;
    }

    @Override
    public BasePage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE_CONFIGURE_VARIABLE));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("'Add Project' page isn't opened");
        }
        return this;
    }

    @Step("Open 'Variables' tab")
    public void clickTabVariables() {
        log.info("Open 'Variables' tab");
        driver.findElement(By.id("users-fields-fields")).click();
        driver.findElement(By.xpath("//*[@id='addConfig']/p/a")).click();
    }

    @Step("Click 'OK' button to save the variable")
    public ConfigureVariablePage clickOkButton() {
        log.info("Click 'OK' button to save the variable");
        driver.findElement(OK_BUTTON).click();
        return this;
    }
}
