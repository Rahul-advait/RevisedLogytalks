package org.testclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class UpcomingConference {
    private WebDriver driver;
    private String UPCOMING_DROPDOWN = ".form-control";
    private String URL = "https://logytalks.com/upcoming-conferences";

    public UpcomingConference(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isOpen() {
        return URL.equalsIgnoreCase(driver.getCurrentUrl());
    }

    public void search(String category) {
        Select selectCourse = new Select(driver.findElement(By.cssSelector(UPCOMING_DROPDOWN)));
        selectCourse.selectByVisibleText(category);
    }
}

