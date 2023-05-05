package PageClasses;

import Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class NavigationBar extends BasePage {
    private static boolean firstTime = true;
    private WebDriver driver;
    private String LIBRARY = "Library";
    private String CONFERENCES = "linkText=>Conferences";
    private String CONFERENCES_CATEGORIES = "cssSelector=>.dropdown-menu.show > a";
    private String SUMMITS = "Summits";
    private String NETWORK = "Network";
    private String ROOMS = "Rooms";
    private String POP_UP_CLOSE_BTN = "cssSelector=>.introjs-skipbutton";
    private String LOGIN_LINK = "linkText=>LOGIN";
    private String POP_BTN = "xpath=>/html/body/div[1]/div/div/button";
    private String PROFILE = "cssSelector=>button#dropdownMenuButton";
    private String LOGOUT_BTN = "cssSelector=>.logout-btn a";

    public NavigationBar(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public LoginPage clickLogin() {
        if (firstTime) {
            clickWhenReady(POP_UP_CLOSE_BTN, 30);
            elementClick(LOGIN_LINK, "Click on login btn");
            firstTime = false;
        } else {
            elementClick(LOGIN_LINK, "Click on login btn");
        }
        return new LoginPage(driver);
    }

    public boolean isUserLoggedIn() {
        return isElementPresent(PROFILE, " Finding profile");
    }

    public void clickLogOut() {
        elementClick(PROFILE, "profile");
        clickWhenReady(LOGOUT_BTN, 30);
    }

    public void cutPopUp() {
        clickWhenReady(POP_UP_CLOSE_BTN, 30);
    }

    public NavigationBar clickConferenceCategory(String categoryType) {
        elementClick(CONFERENCES, "click conference btn");
        List<WebElement> categoryList = getElementList(CONFERENCES_CATEGORIES, "Get conferences category list");
        for (WebElement category : categoryList) {
            if (category.getText().equalsIgnoreCase(categoryType)) {
                String categoryText = category.getText();
                category.click();
                return switch (categoryText) {
                    case "My Conferences" -> new MyConferencePage(driver);
                    case "Ongoing" -> new OngoingConferencePage(driver);
                    case "Upcoming" -> new UpcomingConference(driver);
                    case "Previous" -> new PreviousConferencePage(driver);
                    case "Trending" -> new TrendingConferencePage(driver);
                    default -> new NavigationBar(driver);
                };
            }
        }
        return new NavigationBar(driver);
    }

}
