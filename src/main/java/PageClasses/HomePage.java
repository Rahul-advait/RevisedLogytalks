package PageClasses;

import Utilities.Constants;
import org.openqa.selenium.WebDriver;


import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private String URL = Constants.BASE_URL+ "/home";


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isOpen() {

        return URL.equalsIgnoreCase(driver.getCurrentUrl());
    }


}
