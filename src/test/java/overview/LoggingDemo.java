package overview;

import PageClasses.LoginPage;
import PageClasses.NavigationBar;
import Utilities.Constants;
import base.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class LoggingDemo {

    public static WebDriver driver;


    public static void main(String[] args) {
        String driverPath, driverValue = "", driverKey = "";
        String directory = Constants.USER_DIRECTORY + Constants.DRIVERS_DIRECTORY;
        driverKey = Constants.CHROME_DRIVER_KEY;
        driverValue = Constants.CHROME_DRIVER_VALUE;
        driverPath = directory + driverValue + ".exe";

        System.setProperty(driverKey, driverPath);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 1);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-notifications");

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.google.com/");
        // identify element
        WebElement p=driver.findElement(By.id("inputWrapper"));
        //enter text with sendKeys() then apply submit()
        p.sendKeys("Selenium Java");
        // Explicit wait condition for search results
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
        w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul")));
        p.submit();
        driver.close();
    }
}
