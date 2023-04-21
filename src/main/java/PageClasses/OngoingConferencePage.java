package PageClasses;

import org.openqa.selenium.WebDriver;

public class OngoingConferencePage extends NavigationBar{
    private WebDriver driver;
    public OngoingConferencePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}
