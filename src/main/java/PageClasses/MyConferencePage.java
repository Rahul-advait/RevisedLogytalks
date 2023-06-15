package PageClasses;

<<<<<<< HEAD
import Utilities.Util;
import org.openqa.selenium.WebDriver;

public class MyConferencePage extends NavigationBar{

=======
import Utilities.Constants;
import org.openqa.selenium.WebDriver;

public class MyConferencePage extends NavigationBar {
>>>>>>> c56bd9bfbaf0f852ea1e6355a5ba45e68e51213c
    private WebDriver driver;
    private String myConferenceUrl = Constants.BASE_URL + "/my-conferences";


    public MyConferencePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isOpen() {
//      return getCurrentUrl().equalsIgnoreCase(myConferenceUrl);
        return Util.verifyTextMatch(getCurrentUrl(), myConferenceUrl);
    }
}
