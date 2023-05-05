package org.testclasses;

import PageClasses.LoginPage;
import PageClasses.NavigationBar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class RoughTest {

    private WebDriver driver;
    private NavigationBar navigationBar;
    private LoginPage login;
    private String POP_BTN = ".close";

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        String baseURL = "https://logytalks.com/";
        driver.get(baseURL);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void rough() {
        WebElement element = driver.findElement(By.cssSelector(POP_BTN));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        boolean condition = true;
//        while (condition) {
//            wait.until(ExpectedConditions.elementToBeClickable(element));
//        }

        while (condition) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }
        element.click();
        condition = false;
    }
}
