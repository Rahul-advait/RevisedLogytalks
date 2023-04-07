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

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String baseURL = "https://logytalks.com/";
        driver.get(baseURL);
    }

    @Test
    public void verifyUpcomingFilter() {
        LoginPage login = new LoginPage(driver);
        login.open();
        UpcomingConference upcomingConferencePage = login.signInWith("rahulsingh@yopmail.com", "test@123");

        UpcomingConferenceResultPage filterResult = upcomingConferencePage.filter("Business");

        boolean searchResult = filterResult.isOpen();
        assertTrue(searchResult);
    }

    @Test(dependsOnMethods = "verifyUpcomingFilter")
    public void verifyShareYourKnowledgeLink() {
        UpcomingConferenceResultPage upcomingConferenceResultPage = new UpcomingConferenceResultPage(driver);
        HomePage homepage = upcomingConferenceResultPage.clickShareYourKnowledgeLink();
        boolean clickResult = homepage.isOpen();
        Assert.assertTrue(clickResult);
    }

    @AfterClass
    public void tearDown() {
//        driver.quit();
    }


}
