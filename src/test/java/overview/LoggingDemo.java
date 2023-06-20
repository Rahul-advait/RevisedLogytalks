package overview;

import PageClasses.LoginPage;
import PageClasses.NavigationBar;
import Utilities.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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
        options.addArguments("disable-infobars");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);

        String baseURL = Constants.BASE_URL;
        driver.get(baseURL);
        NavigationBar navigationBar = new NavigationBar(driver);
        LoginPage login = navigationBar.clickLoginLink();
        driver.quit();
        driver.close();
    }
}
