package base;

import PageClasses.LoginPage;
import PageClasses.NavigationBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class BaseClassTest {
    protected WebDriver driver;
    protected NavigationBar navigationBar;
    protected LoginPage login;


    @BeforeClass
    public void beforeSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        String baseURL = "https://logytalks.com/";
        driver.get(baseURL);
        navigationBar = new NavigationBar(driver);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
