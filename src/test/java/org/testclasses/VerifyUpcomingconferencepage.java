package org.testclasses;

import PageClasses.HomePage;
import PageClasses.MyConferencePage;
import PageClasses.UpcomingConference;
import PageClasses.UpcomingConferenceResultPage;
import Utilities.Constants;
import base.BaseClassTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class VerifyUpcomingconferencepage extends BaseClassTest {
    private UpcomingConference upcomingConference;

    @BeforeClass
    public void setUp() {
        navigationBar = login.signInWith(Constants.DEFAULT_USERNAME, Constants.DEFAULT_PASSWORD);
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
        MyConferencePage myConference = (MyConferencePage) navigationBar.clickConferenceCategory(
                "My Conferences");
        boolean checkMyconferenceUrl = myConference.isOpen();
        myConference.cutPopUp();
        Assert.assertTrue(checkMyconferenceUrl);
    }

}
