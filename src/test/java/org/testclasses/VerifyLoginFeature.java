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
        if (!navigationBar.isOpen("login")) {
            navigationBar.clickLoginLink();
        }
    }

    // Verify user is able to login with valid credentials
    @Test
    public void validCredentials() {
        navigationBar = login.signInWith(Constants.DEFAULT_USERNAME, Constants.DEFAULT_PASSWORD);
        boolean headerResult = navigationBar.verifyHeader();
        CheckPoint.mark("TC-01", headerResult, "header verification");
        boolean result = navigationBar.isUserLoggedIn();
        CheckPoint.markFinal("TC_02", result, "profile icon verification");
    }

    // Verify user is unable to login invalid credentials
    @Test
    public void invalidEmail() {
        navigationBar = login.signInWith("rahulsinghas@yopmail.com", "Test@123");
        boolean loggedIn = navigationBar.isUserLoggedIn();
        CheckPoint.mark("TC-01", !loggedIn, "Login verification");
        boolean alert = login.isInvalidAlertPresent();
        CheckPoint.markFinal("TC-02", alert, "Alert verification");
    }

    //Verify user is not able to login with empty email
    @Test
    public void emptyEmail() {
        navigationBar = login.signInWith("", Constants.DEFAULT_PASSWORD);
        boolean result = navigationBar.isUserLoggedIn();
        assertFalse(result);
    }

    /// Verify user is unable to login with invalid password
    @Test
    public void invalidPassword() {
        navigationBar = login.signInWith(Constants.DEFAULT_USERNAME, Constants.INVALID_PASSWORD);
        boolean result = navigationBar.isUserLoggedIn();
        assertFalse(result);
    }

    // Verify user is unable to login with empty password
    @Test
    public void emptyPassword() {
        navigationBar = login.signInWith(Constants.DEFAULT_USERNAME, "");
        boolean result = navigationBar.isUserLoggedIn();
        CheckPoint.mark("TC=01", !result, "Check logged in ");
        boolean isPassReq = login.getAttribute();
        CheckPoint.markFinal("Tc-02", isPassReq, "Password required");
    }

    //Verify user's session remains even after closing curren tab
    @Test
    public void testRememberMe() {
        navigationBar = login.signInWith(Constants.DEFAULT_USERNAME, Constants.DEFAULT_PASSWORD, true);
        navigationBar.closeCurrentOpenNew("upcoming-conferences");
        boolean result = navigationBar.isUserLoggedIn();
        assertTrue(result);
    }

    // Verify user navigates to sign up page after clicking on sign up button
    @Test
    public void signUpRedirect() {
        login.clickSignUpBtn();
        boolean result = login.isOpen("register");
        assertTrue(result);
    }

    // Verify user is able to login with google sign up
    @Test
    public void googleSignUp() {
        homePage = login.clickGoogleSignUpBtn();
        navigationBar = homePage.cutPopUp();
        boolean result = navigationBar.isUserLoggedIn();
        assertTrue(result);
    }
}
