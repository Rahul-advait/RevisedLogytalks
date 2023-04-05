package org.testclasses;

import PageClasses.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class VerifyUpcomingDropdown {
    WebDriver driver;
    String baseURL;
    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        baseURL = "https://logytalks.com/";
        driver.get(baseURL);
    }

    @Test
    public void verify() {
        LoginPage login = new LoginPage(driver);
        login.open();
        login.signInWith("rahulsingh@yopmail.com", "test@123");
        UpcomingConference upcomingConferencePage = new UpcomingConference(driver);
//        Assert.assertTrue(upcomingConferencePage.isOpen());
        upcomingConferencePage.search("Engineering");
//        boolean searchResult = verifySearchResult();
    }
    @AfterClass
    public void tearDown() {
//        driver.quit();
    }


}
