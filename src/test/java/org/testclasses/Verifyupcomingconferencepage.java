package org.testclasses;

import PageClasses.HomePage;
import PageClasses.MyConferencePage;
import PageClasses.UpcomingConference;
import PageClasses.UpcomingConferenceResultPage;
import base.BaseClassTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


<<<<<<< HEAD:src/test/java/org/testclasses/verifyupcomingconferencepage.java
public class verifyupcomingconferencepage extends BaseClassTest {
=======
public class Verifyupcomingconferencepage extends BaseClassTest {
>>>>>>> 3f37352f23eca7716357c617846fe601d40a3d22:src/test/java/org/testclasses/Verifyupcomingconferencepage.java
    private UpcomingConference upcomingConference;

    @BeforeClass
    public void setUp() {
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
        MyConferencePage myConference = (MyConferencePage) navigationBar.clickConferenceCategory(
                "My Conferences");
        boolean checkMyconferenceUrl = myConference.isOpen();
        myConference.cutPopUp();
        Assert.assertTrue(checkMyconferenceUrl);
    }

}
