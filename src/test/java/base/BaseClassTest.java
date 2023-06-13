package base;

import PageClasses.LoginPage;
import PageClasses.NavigationBar;
import Utilities.Constants;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseClassTest {
    public WebDriver driver;
    protected NavigationBar navigationBar;
    protected LoginPage login;

    @BeforeClass
    public void beforeSetUp() {
        driver = WebDriverFactory.getInstance().getDriver("chrome");
        String baseURL = Constants.BASE_URL;
        driver.get(baseURL);
        navigationBar = new NavigationBar(driver);
        login = navigationBar.clickLogin();
    }

    @BeforeMethod
    public void methodSetUp() {
        CheckPoint.clearHashMap();
    }

    @AfterClass
    public void tearDown() {
        WebDriverFactory.getInstance().quitDriver();
    }
}
