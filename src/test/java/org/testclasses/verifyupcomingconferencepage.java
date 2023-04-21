package org.testclasses;

import PageClasses.*;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class verifyupcomingconferencepage {
    private WebDriver driver;
    private NavigationBar navigationBar;
    private LoginPage login;

    private UpcomingConference upcomingConference;

    @BeforeClass
    public void setUp() throws InterruptedException {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        String baseURL = "https://logytalks.com/";
        driver.get(baseURL);
        login = new LoginPage(driver);
        login.open();
        navigationBar = login.signInWith("rahulsingh@yopmail.com", "Test@123");
    }


//    @Test
//    public void verifyUpcomingFilter() {
//        upcomingConference = (UpcomingConference) navigationBar.clickConferenceCategory("Upcoming");
//        UpcomingConferenceResultPage filterResult = upcomingConference.filter("Business");
//        boolean searchResult = filterResult.isOpen();
//        Assert.assertTrue(searchResult);
//    }

    @Test
    public void verifyShareYourKnowledgeLink() {
        upcomingConference = (UpcomingConference) navigationBar.clickConferenceCategory("Upcoming");
        HomePage homepage = upcomingConference.clickShareYourKnowledgeLink();
        boolean clickResult = homepage.isOpen();
        upcomingConference.cutPopUp();
        Assert.assertTrue(clickResult);
    }

//    @Test
//    public void verifyLogin() {
//        MyConferencePage myConference = (MyConferencePage) navigationBar.clickConferenceCategory("My Conferences");
//        boolean checkMyconferenceUrl = myConference.isOpen();
//        myConference.cutPopUp();
//        Assert.assertTrue(checkMyconferenceUrl);
//    }

    @AfterClass
    public void tearDown() {
//        driver.quit();
    }


}
