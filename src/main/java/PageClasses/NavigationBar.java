package PageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class NavigationBar {
    private WebDriver driver;
    private String LIBRARY = "Library";
    private String LIBRARY_MENU = ".dropdown-menu.show > .dropdown-item";
    private String CONFERENCE = "Conferences";
    private String CONFERENCE_MENU = ".dropdown-menu.show > .dropdown-item";
    private String SUMMIT = "Summits";
    private String SUMMIT_MENU = "li > .dropdown-item";
    private String UPCOMING = "Upcoming »";
    private String UPCOMING_MENU = "li:nth-of-type(1) > .dropdown-menu.submenu > a";
    private String PREVIOUS = "Previous »";
    private String PREVIOUS_MENU = "li:nth-of-type(2) > .dropdown-menu.submenu > a";

    public NavigationBar(WebDriver driver) {
        this.driver = driver;
    }

    public void clickUpcomingBtn(String conferenceType, int index) {
        clickMenuBtn(SUMMIT, SUMMIT_MENU, conferenceType);
        if (conferenceType.equalsIgnoreCase("upcoming")) {
            clickMenuItem(UPCOMING_MENU, index);
        } else if (conferenceType.equalsIgnoreCase("previous")) {
            clickMenuItem(PREVIOUS_MENU, index);
        }
    }


    public void clickLibraryBtn(String menuItem) {
        clickMenuBtn(CONFERENCE, CONFERENCE_MENU, menuItem);
    }

    public void clickConferenceBtn(String menuItem) {
        clickMenuBtn(LIBRARY, LIBRARY_MENU, menuItem);
    }

    private void clickMenuBtn(String menuLink, String menuItems, String menuItem) {
        driver.findElement(By.linkText(menuLink)).click();
        clickMenuItem(menuItems, menuItem);
    }

    private void clickMenuItem(String menuItems, String menuItem) {
        List<WebElement> items = driver.findElements(By.cssSelector(menuItems));
        for (WebElement item : items) {
            if (item.getText().toLowerCase().contains(menuItem.toLowerCase())) {
                item.click();
                break;
            }
        }
    }

    private void clickMenuItem(String menuItems, int index) {
        List<WebElement> items = driver.findElements(By.cssSelector(menuItems));
        items.get(index).click();
    }
}

