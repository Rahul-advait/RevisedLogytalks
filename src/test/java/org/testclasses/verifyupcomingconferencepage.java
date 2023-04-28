package org.testclasses;

import PageClasses.*;
import base.BaseClassTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class verifyupcomingconferencepage extends BaseClassTest {
    private WebDriver driver;
    private NavigationBar navigationBar;
    private LoginPage login;

    private UpcomingConference upcomingConference;

    @BeforeClass
    public void setUp() {
        super.setUp();
        navigationBar = login.signInWith("rahulsingh@yopmail.com", "Test@123");
    }


    @Test
    public void verifyUpcomingFilter() {
        upcomingConference = (UpcomingConference) navigationBar.clickConferenceCategory("Upcoming");
        UpcomingConferenceResultPage filterResult = upcomingConference.filter("Business");
        boolean searchResult = filterResult.isOpen();
        Assert.assertTrue(searchResult);
    }

    @Test
    public void verifyShareYourKnowledgeLink() {
        upcomingConference = (UpcomingConference) navigationBar.clickConferenceCategory("Upcoming");
        HomePage homepage = upcomingConference.clickShareYourKnowledgeLink();
        boolean clickResult = homepage.isOpen();
        upcomingConference.cutPopUp();
        Assert.assertTrue(clickResult);
    }

    @Test
    public void verifyLogin() {
        MyConferencePage myConference = (MyConferencePage) navigationBar.clickConferenceCategory("My Conferences");
        boolean checkMyconferenceUrl = myConference.isOpen();
        myConference.cutPopUp();
        Assert.assertTrue(checkMyconferenceUrl);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }


}
