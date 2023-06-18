package Base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class BasePage extends CustomDriver {

    private static final Logger log = LogManager.getLogger(CustomDriver.class.getName());
    protected WebDriver driver;
    private String SIGN_UP = "cssSelector=>.tp_btn_primary";
    private String NOT_NOW_BTN = "cssSelector=>.tp_btn_default";

    public BasePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public boolean verifyTitle(String expectedTitle) {
        return driver.getTitle().equalsIgnoreCase(expectedTitle);
    }

    public boolean isOpen(String url) {
        log.info("Checking to see if current url contains " + url);
        return driver.getCurrentUrl().contains(url);
    }

    public void closeNotification() {
        elementClick(NOT_NOW_BTN, "Not Now Button");
    }

    public void signUpNotification() {
        log.info("Goint to try clicking on sign up button");
        elementClick(SIGN_UP, "Sign Up Button");
    }
}

