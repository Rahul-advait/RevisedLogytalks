package PageClasses;

import Utilities.Util;
import org.openqa.selenium.WebDriver;

public class MyConferencePage extends NavigationBar{

    private WebDriver driver;
    private String myConferenceUrl = "https://logytalks.com/my-conferences";


    public MyConferencePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public boolean isOpen() {
//      return getCurrentUrl().equalsIgnoreCase(myConferenceUrl);
        return Util.verifyTextMatch(getCurrentUrl(), myConferenceUrl);
    }
}
