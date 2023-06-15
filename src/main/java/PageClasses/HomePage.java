package PageClasses;

<<<<<<< HEAD
import Utilities.Util;
=======
import Utilities.Constants;
>>>>>>> c56bd9bfbaf0f852ea1e6355a5ba45e68e51213c
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
