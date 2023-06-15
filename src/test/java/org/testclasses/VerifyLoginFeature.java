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
            navigationBar.clickLogin();
        }
    }

    @Test
    public void validCredentials() {
        navigationBar = login.signInWith(Constants.DEFAULT_USERNAME, Constants.DEFAULT_PASSWORD);
        boolean headerResult = navigationBar.verifyHeader();
//        assertTrue(headerResult);
        CheckPoint.mark("TC-01", headerResult, "header verification");
        boolean result = navigationBar.isUserLoggedIn();
//        assertTrue(result);
        CheckPoint.markFinal("TC_02", result, "profile icon verification");
    }

    @Test(enabled = false)
    public void invalidCred() {
        navigationBar = login.signInWith("rahulsinghas@yopmail.com", "Test@123");
        boolean result = navigationBar.isUserLoggedIn();
        assertFalse(result);
    }


}
