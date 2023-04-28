package org.testclasses;

import PageClasses.LoginPage;
import PageClasses.NavigationBar;
import base.BaseClassTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class VerifyLoginFeature extends BaseClassTest {
    private WebDriver driver;
    private NavigationBar navigationBar;
    private LoginPage login;


    @BeforeClass
    public void setUp() {
        super.setUp();
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

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
