package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class Picklist {

    private final String PICKLIST_PATTERN = "//div[contains(@class,'column')]/label[normalize-space(text())='%s']//" +
            "following-sibling::div";

    WebDriver driver;
    String label;

    public Picklist(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void select(String option) {
        log.info("Selecting '{}' inside picklist '{}'", option, label);
        driver.findElement(By.xpath(String.format(PICKLIST_PATTERN, label))).click();
        driver.findElement(By.xpath(String.format(PICKLIST_PATTERN + "//li[contains(text(),'%s')]", label, option))).click();
    }
}
