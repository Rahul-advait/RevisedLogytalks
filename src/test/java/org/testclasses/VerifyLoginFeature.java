package org.testclasses;

import Utilities.Constants;
import base.BaseClassTest;
import base.CheckPoint;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class VerifyLoginFeature extends BaseClassTest {

    @BeforeClass
    public void setUp() {
    }

    @AfterMethod
    public void afterMethod() {
        if (navigationBar.isUserLoggedIn()) {
            navigationBar.clickLogOut();
            navigationBar.clickLoginLink();
        }
    }

    @Test
    public void validCredentials() {
        navigationBar = login.signInWith(Constants.DEFAULT_USERNAME, Constants.DEFAULT_PASSWORD);
        boolean headerResult = navigationBar.verifyHeader();
        CheckPoint.mark("TC-01", headerResult, "header verification");
        boolean result = navigationBar.isUserLoggedIn();
        CheckPoint.markFinal("TC_02", result, "profile icon verification");
    }

    @Test
    public void invalidEmail() {
        navigationBar = login.signInWith("rahulsinghas@yopmail.com", "Test@123");
        boolean result = navigationBar.isUserLoggedIn();
        assertFalse(result);
    }

    @Test
    public void emptyEmail() {
        navigationBar = login.signInWith("", Constants.DEFAULT_PASSWORD);
        boolean result = navigationBar.isUserLoggedIn();
        assertFalse(result);
    }

    @Test
    public void invalidPassword() {
        navigationBar = login.signInWith(Constants.DEFAULT_USERNAME, Constants.INVALID_PASSWORD);
        boolean result = navigationBar.isUserLoggedIn();
        assertFalse(result);
    }

    @Test
    public void emptyPassword() {
        navigationBar = login.signInWith(Constants.DEFAULT_USERNAME, "");
        boolean result = navigationBar.isUserLoggedIn();
        assertFalse(result);
    }

    @Test
    public void testRememberMe() {
        navigationBar = login.signInWith(Constants.DEFAULT_USERNAME, Constants.DEFAULT_PASSWORD, true);
        navigationBar.closeCurrentOpenNew("upcoming-conferences");
        boolean result = navigationBar.isUserLoggedIn();
        assertTrue(result);
    }

    @Test
    public void signUpRedirect() {
        login.clickSignUpBtn();
        boolean result = login.isOpen("register");
        assertTrue(result);
    }
}
