package PageClasses;

import Base.BasePage;
import Utilities.Constants;
import Utilities.Util;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final WebDriver driver;
    private String URL = "login";
    private String GOOGLE_PASWD_NEXT = "xpath=>//div[@id='passwordNext']//button";
    private String GOOGLE_PASSWORD = "cssSelector=>input[name='Passwd']";
    private String GOOGLE_EMAIL = "cssSelector=>input#identifierId";
    private String GOOGLE_NEXT_BTN = "xpath=>//div[@id='identifierNext']//button";
    private String GOOGLE_BTN = "cssSelector=>.other-login-right > a:nth-of-type(2)";
    private String SIGN_UP = "cssSelector=>.term-condition-a > b";
    private String REMEMBER_ME_BTN = "cssSelector=>input#remember";
    private String EMAIL_FIELD = "xpath=>//input[@id='email']";
    private String PASSWORD_FIELD = "xpath=>//input[@id='password']";
    private String LOG_IN_BTN = "cssSelector=>.update-profile-btn1";
    private String INVALID_ALERT = "cssSelector=>[role='alert']";
    private String FORGOT_PASS = "cssSelector=>.forgot-password";

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public ForgotPassword clickForgotPas() {
        elementClick(FORGOT_PASS, "Forgot Password");
        return new ForgotPassword(driver);
    }

    public boolean isOpen(){
        return super.isOpen(URL);
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

    public RegistrationPage clickSignUpBtn() {
        elementClick(SIGN_UP, "You don't have an account? Sign up");
        return new RegistrationPage(driver);
    }

    public HomePage withGoogle() {
//        elementClick(GOOGLE_BTN, "Google Sign Up btn");
        waitThenClick(GOOGLE_BTN, 30, "Google Sign Up btn");
        waitThenSendData(GOOGLE_EMAIL, 30, Constants.GOOGLE_EMAIL, "Google Email Address");

        elementClick(GOOGLE_NEXT_BTN, "Google Next Btn");

        waitThenSendData(GOOGLE_PASSWORD, 30, Constants.GOOGLE_PASS, "Google password");
        elementClick(GOOGLE_PASWD_NEXT, "Google Password Next Btn");
        return new HomePage(driver);
    }

    public boolean isInvalidAlertPresent() {
        return isDisplayed(INVALID_ALERT, "Invalid credential alert");
    }

    public boolean isRequired(String field) {
        String attributePresence = "";
        if (field.contains("email")) {
            attributePresence = getElementAttributeValue(EMAIL_FIELD, "required");
        } else if (field.contains("password")) {
            attributePresence = getElementAttributeValue(PASSWORD_FIELD, "required");
        }

        return attributePresence.contains("true");
    }

    public boolean fieldType(String field, String attribute, String attributeValue) {
        String getAttributeValue = "";
        if (field.contains("password")) {
            getAttributeValue = getElementAttributeValue(PASSWORD_FIELD, attribute);
        }
        return Util.verifyTextMatch(getAttributeValue, attributeValue);
    }

    public void clickGoogleSignUpBtn() {
        elementClick(GOOGLE_BTN, "Google btn");
    }
}
