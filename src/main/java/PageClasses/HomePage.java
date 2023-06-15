package PageClasses;

import Utilities.Util;
import Utilities.Constants;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private String URL = Constants.BASE_URL+ "/home";


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isOpen() {
        return Util.verifyTextMatch(driver.getCurrentUrl(), URL);
    }


}
