package PageClasses;

import org.openqa.selenium.WebDriver;

public class PreviousConferencePage extends NavigationBar{
    private WebDriver driver;
    public PreviousConferencePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}
