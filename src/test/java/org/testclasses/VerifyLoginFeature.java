package org.testclasses;

import base.BaseClassTest;
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
        navigationBar = login.signInWith("rahulsingh@yopmail.com", "Test@123");
        boolean result = navigationBar.isUserLoggedIn();
        assertTrue(result);
    }

    @Test
    public void invalidCred() {
        navigationBar = login.signInWith("rahulsinghas@yopmail.com", "Test@123");
        boolean result = navigationBar.isUserLoggedIn();
        assertFalse(result);
    }


}
