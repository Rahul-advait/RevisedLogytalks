package org.testclasses;

import PageClasses.HomePage;
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

import static org.testng.Assert.assertTrue;

public class verifyupcomingconferencepage {
    private WebDriver driver;
    private UpcomingConference upcomingConferencePage;
    private LoginPage login;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String baseURL = "https://logytalks.com/";
        driver.get(baseURL);
        login = new LoginPage(driver);
        login.open();
        upcomingConferencePage = login.signInWith("rahulsingh@yopmail.com", "test@123");
    }

    @Test
    public void verifyUpcomingFilter() {
        UpcomingConferenceResultPage filterResult = upcomingConferencePage.filter("Business");
        boolean searchResult = filterResult.isOpen();
        assertTrue(searchResult);
    }

    @Test
    public void verifyShareYourKnowledgeLink() {
        upcomingConferencePage = new UpcomingConference(driver);
        driver.get(upcomingConferencePage.getURL());
        HomePage homepage = upcomingConferencePage.clickShareYourKnowledgeLink();
        boolean clickResult = homepage.isOpen();
        Assert.assertTrue(clickResult);
    }

    @AfterClass
    public void tearDown() {
//        driver.quit();
    }


}