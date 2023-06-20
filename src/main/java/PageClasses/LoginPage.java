package PageClasses;

import Base.BasePage;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private String GOOGLE_PASWD_NEXT = "xpath=>//div[@id='passwordNext']//button";
    private String GOOGLE_PASSWORD = "cssSelector=>input[name='Passwd']";
    private String GOOGLE_EMAIL = "cssSelector=>input#identifierId";
    private String GOOGLE_NEXT_BTN = "xpath=>//div[@id='identifierNext']//button";
    private String GOOGLE_BTN = "cssSelector=>.other-login-right > a:nth-of-type(2)";
    private String SIGN_UP = "cssSelector=>.term-condition-a > b";
    private WebDriver driver;
    //    private String REMEMBER_ME_BTN = "cssSelector=>.checkmark";
    private String REMEMBER_ME_BTN = "cssSelector=>input#remember";
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
        } else {
            if (isSelected(REMEMBER_ME_BTN, "Remember Me Button")) {
                elementClick(REMEMBER_ME_BTN, "Remember Me Button");
            }
        }
        elementClick(LOG_IN_BTN, "Login Btn");
        return new NavigationBar(driver);
    }

    public void clickSignUpBtn() {
        elementClick(SIGN_UP, "You don't have an account? Sign up");
    }

    public HomePage clickGoogleSignUpBtn() {
        elementClick(GOOGLE_BTN, "Google Sign Up btn");
        sendData(GOOGLE_EMAIL, "pequirebicep@gmail.com", "Google Email Address");
        elementClick(GOOGLE_NEXT_BTN, "Google Next Btn");
        sendData(GOOGLE_PASSWORD, "1@mThestar", "Google password");
        elementClick(GOOGLE_PASWD_NEXT, "Google Password Next Btn");
        return new HomePage(driver);
    }
}
