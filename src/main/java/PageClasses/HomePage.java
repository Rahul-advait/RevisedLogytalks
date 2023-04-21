package PageClasses;

import org.openqa.selenium.WebDriver;


import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private String URL = "https://logytalks.com/home";


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isOpen() {

        return URL.equalsIgnoreCase(driver.getCurrentUrl());
    }


}
