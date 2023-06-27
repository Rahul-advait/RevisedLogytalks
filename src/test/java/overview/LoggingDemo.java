package overview;

import Base.CustomDriver;
import Utilities.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class LoggingDemo {

    public static WebDriver driver;


    public static void main(String[] args) {
        try {
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
            options.addArguments("--incognito");

            WebDriver driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.get("https://logytalks.com/");
            CustomDriver customDriver = new CustomDriver(driver);
            customDriver.closeCurrentOpenNew("");
        } catch (Exception e) {
            driver.close();
            driver.quit();
        }
        driver.close();
        driver.quit();
    }
}
