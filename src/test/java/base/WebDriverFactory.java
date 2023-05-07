package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverFactory {
    private static final WebDriverFactory instance = new WebDriverFactory();
    private static ThreadLocal<WebDriver> threadedDriver = new ThreadLocal<WebDriver>();

    private WebDriverFactory() {
    }

    public static WebDriverFactory getInstance() {
        return instance;
    }

    public WebDriver getDriver(String browser) {
        WebDriver driver = null;
        if (threadedDriver.get() == null) {
            try {
                if (browser.equalsIgnoreCase("firefox")) {
                    driver = new FirefoxDriver();
                    threadedDriver.set(driver);
                }
                if (browser.equalsIgnoreCase("chrome")) {
                    driver = new ChromeDriver();
                    threadedDriver.set(driver);
                }
                if (browser.equalsIgnoreCase("iexplorer")) {
                    driver = new InternetExplorerDriver();
                    threadedDriver.set(driver);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            threadedDriver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            threadedDriver.get().manage().window().maximize();
        }
        return threadedDriver.get();
    }

    public void quitDriver() {
        threadedDriver.get().quit();
        threadedDriver.set(null);
    }
}
