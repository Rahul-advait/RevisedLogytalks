package PageClasses;

import org.openqa.selenium.WebDriver;

public class TrendingConferencePage extends NavigationBar{
    private WebDriver driver;
    public TrendingConferencePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

}
