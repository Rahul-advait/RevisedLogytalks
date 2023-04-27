package PageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;
    private String EMAIL_FIELD = "//input[@id='email']";
    //    private String LOGIN_LINK = "LOGIN";
    private String PASSWORD_FIELD = "//input[@id='password']";
    private String LOG_IN_BTN = ".update-profile-btn1";

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }


    public NavigationBar signInWith(String email, String password) {
        WebElement emailField = driver.findElement(By.xpath(EMAIL_FIELD));
        emailField.clear();
        emailField.sendKeys(email);

        WebElement passwordField = driver.findElement(By.xpath(PASSWORD_FIELD));
        passwordField.clear();
        passwordField.sendKeys(password);

        WebElement loginBtn = driver.findElement(By.cssSelector(LOG_IN_BTN));
        loginBtn.click();

        return new NavigationBar(driver);
    }
}
