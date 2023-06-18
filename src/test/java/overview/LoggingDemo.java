package overview;

import Utilities.Constants;
import base.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class LoggingDemo{

    public static WebDriver driver;


    public static void main(String[] args) {
        driver = WebDriverFactory.getInstance().getDriver("chrome");
        String baseURL = Constants.BASE_URL;
        driver.get(baseURL);

        String originalWindow = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
        String newWindow = driver.getWindowHandle();

        System.out.println(originalWindow + " " + newWindow);
    }
}
