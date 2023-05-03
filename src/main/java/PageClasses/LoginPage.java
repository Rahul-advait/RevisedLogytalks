package PageClasses;

import Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    private WebDriver driver;
    private String EMAIL_FIELD = "xpath=>//input[@id='email']";
    private String PASSWORD_FIELD = "xpath=>//input[@id='password']";
    private String LOG_IN_BTN = "cssSelector=>.update-profile-btn1";

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


    public NavigationBar signInWith(String email, String password) {
        sendData(EMAIL_FIELD, email, "Entering Email");

        sendData(PASSWORD_FIELD, password, "Entering Password");

        elementClick(LOG_IN_BTN, "Clicked on Login Btn");

        return new NavigationBar(driver);
    }
}
