package PageClasses;

import Base.BasePage;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private String SIGN_UP = "cssSelector=>.term-condition-a > b";
    private WebDriver driver;
    private String REMEMBER_ME_BTN = "cssSelector=>.checkmark";
    private String EMAIL_FIELD = "xpath=>//input[@id='email']";
    private String PASSWORD_FIELD = "xpath=>//input[@id='password']";
    private String LOG_IN_BTN = "cssSelector=>.update-profile-btn1";

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


    public NavigationBar signInWith(String email, String password) {
        return signInWith(email, password, true);
    }

    public NavigationBar signInWith(String email, String password, boolean rememberMe) {
        sendData(EMAIL_FIELD, email, "Entering Email");
        sendData(PASSWORD_FIELD, password, "Entering Password");
        if (rememberMe) {
            if (!isSelected(REMEMBER_ME_BTN, "Remember Me Button")) {
                elementClick(REMEMBER_ME_BTN, "Remember Me Button");
            }
        }
        elementClick(LOG_IN_BTN, "Login Btn");
        return new NavigationBar(driver);
    }

    public void clickSignUpBtn() {
        elementClick(SIGN_UP, "You don't have an account? Sign up");
    }
}
