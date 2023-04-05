package org.testclasses;

import PageClasses.LoginPage;
import PageClasses.UpcomingConference;
import PageClasses.UpcomingConferenceResultPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class VerifyUpcomingDropdown {
    private WebDriver driver;
    private String baseURL;
    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
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
        upcomingConferencePage.search("Business");

        UpcomingConferenceResultPage resultPage = new UpcomingConferenceResultPage();
        boolean searchResult = resultPage.isOpen();
        assertTrue(searchResult);
    }
    @AfterClass
    public void tearDown() {
//        driver.quit();
    }


}
