package base;

import PageClasses.LoginPage;
import PageClasses.NavigationBar;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClassTest {
    public WebDriver driver;
    protected NavigationBar navigationBar;
    protected LoginPage login;

    @BeforeClass
    public void beforeSetUp() {
        driver = WebDriverFactory.getInstance().getDriver("firefox");
        String baseURL = "https://logytalks.com/";
        driver.get(baseURL);
        navigationBar = new NavigationBar(driver);
        login = navigationBar.clickLogin();
    }


    @AfterClass
    public void tearDown() {
        WebDriverFactory.getInstance().quitDriver();
    }
}
