package org.testclasses;

import PageClasses.ForgotPassword;
import PageClasses.RegistrationPage;
import Utilities.Constants;
import base.BaseClassTest;
import base.CheckPoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class VerifyLoginFeature extends BaseClassTest {
    private static Logger log = LogManager.getLogger(VerifyLoginFeature.class.getName());
    ForgotPassword forgotPassword;
    RegistrationPage registrationPage;

    @BeforeClass
    public void setUp() {
    }

    @AfterMethod
    public void afterMethod() {
        String endTest = "------";
        log.info(endTest.repeat(10));
        if (navigationBar.isUserLoggedIn()) {
            navigationBar.clickLogOut();
            navigationBar.clickLoginLink();
        }
        if (!navigationBar.isOpen("login")) {
            navigationBar.clickLoginLink();
        }

    }

    // Verify log in with valid credentials
    @Test(priority = 2)
    public void validCredentials() {
        navigationBar = login.signInWith(Constants.DEFAULT_USERNAME, Constants.DEFAULT_PASSWORD);
        boolean headerResult = navigationBar.verifyHeader();
        CheckPoint.mark("TC-01", headerResult, "header verification");
        boolean result = navigationBar.isUserLoggedIn();
        CheckPoint.markFinal("TC_02", result, "profile icon verification");
    }

    // Verify login invalid credentials
    @Test
    public void invalidEmail() {
        navigationBar = login.signInWith("rahulsinghas@yopmail.com", "Test@123");
        boolean loggedIn = navigationBar.isUserLoggedIn();
        CheckPoint.mark("TC-01", !loggedIn, "Login verification");
        boolean alert = login.isInvalidAlertPresent();
        CheckPoint.markFinal("TC-02", alert, "Alert verification");
    }

    //Verify log in empty email
    @Test
    public void emptyEmail() {
        navigationBar = login.signInWith("", Constants.DEFAULT_PASSWORD);
        boolean result = navigationBar.isUserLoggedIn();
        assertFalse(result);
    }

    /// Verify log in invalid password
    @Test
    public void invalidPassword() {
        navigationBar = login.signInWith(Constants.DEFAULT_USERNAME, Constants.INVALID_PASSWORD);
        boolean result = navigationBar.isUserLoggedIn();
        assertFalse(result);
    }

    // Verify unable to log in with empty password
    @Test
    public void emptyPassword() {
        navigationBar = login.signInWith(Constants.DEFAULT_USERNAME, "");
        boolean result = navigationBar.isUserLoggedIn();
        assertFalse(result);
    }

    //Verify password field is marked
    @Test
    public void maskPassword() {
        boolean verify = login.fieldType("password", "type", "password");
        assertTrue(verify);
    }

    //Verify login fields are required
    @Test
    public void requiredFields() {
        boolean isPassReq = login.isRequired("password");
        CheckPoint.mark("Tc-02", isPassReq, "Password required");

        boolean isEmailReq = login.isRequired("email");
        CheckPoint.markFinal("Tc-02", isEmailReq, "Email required");
    }

    //Verify user's session closing current tab
    @Test(priority = 1)
    public void testRememberMe() {
        navigationBar = login.signInWith(Constants.DEFAULT_USERNAME, Constants.DEFAULT_PASSWORD, true);
        boolean isNewWindow = navigationBar.closeCurrentOpenNew("upcoming-conferences");
        CheckPoint.mark("TC-REM-01", isNewWindow, "New window");
        boolean result = navigationBar.isUserLoggedIn();
        CheckPoint.markFinal("TC-REM-01", result, "Is user logged in");
    }

    // Verify clicking on sign up button
    @Test(priority = 3)
    public void signUpRedirect() {
        registrationPage = login.clickSignUpBtn();
        boolean result = registrationPage.isOpen();
        assertTrue(result);
    }

    // Verify log in Google sign up
    @Test
    public void googleSignUp() {
        homePage = login.withGoogle();
        navigationBar = homePage.cutPopUp();
        boolean result = navigationBar.isUserLoggedIn();
        assertTrue(result);
    }

    @Test
    public void verifyForgotPas() {
        forgotPassword = login.clickForgotPas();
        boolean verify = forgotPassword.isOpen();
        assertTrue(verify);
    }
}
