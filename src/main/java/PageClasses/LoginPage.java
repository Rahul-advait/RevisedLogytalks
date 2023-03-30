package PageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private String POP_BTN = "/html/body/div[1]/div/div/button";
    private String EMAIL_FIELD = "//input[@id='email']";
    private String LOGIN_LINK = "LOGIN";
    private String PASSWORD_FIELD = "//input[@id='password']";
    private String LOG_IN_BTN = ".update-profile-btn1";

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(POP_BTN))));
        driver.findElement(By.xpath(POP_BTN)).click();
        driver.findElement(By.linkText(LOGIN_LINK)).click();
    }

    public void signInWith(String email, String password) {
        WebElement emailField = driver.findElement(By.xpath(EMAIL_FIELD));
        emailField.clear();
        emailField.sendKeys(email);

        WebElement passwordField = driver.findElement(By.xpath(PASSWORD_FIELD));
        passwordField.clear();
        passwordField.sendKeys(password);

        WebElement loginBtn = driver.findElement(By.cssSelector(LOG_IN_BTN));
        loginBtn.click();
    }
}
