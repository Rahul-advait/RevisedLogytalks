package PageClasses;

import Base.BasePage;
import Utilities.Util;
import Utilities.Constants;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private String TUTORIAL_POP_UP = "cssSelector=>.introjs-tooltip-header > a[role='button']";
    private WebDriver driver;
    private String URL = Constants.BASE_URL+ "/home";


    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public boolean isOpen() {
        return Util.verifyTextMatch(driver.getCurrentUrl(), URL);
    }

    public NavigationBar cutPopUp() {
        signUpNotification();
        elementClick(TUTORIAL_POP_UP, "Tutorial pop btn");
        return new NavigationBar(driver);
    }


}
