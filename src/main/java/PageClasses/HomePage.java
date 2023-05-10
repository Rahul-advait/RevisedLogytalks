package PageClasses;

import Utilities.Util;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private String URL = "https://logytalks.com/home";


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isOpen() {
        return Util.verifyTextMatch(driver.getCurrentUrl(), URL);
    }


}
