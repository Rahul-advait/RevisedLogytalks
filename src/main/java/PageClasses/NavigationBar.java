package PageClasses;

import Base.BasePage;
import Utilities.Util;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class NavigationBar extends BasePage {
    private static boolean firstTime = true;
    private WebDriver driver;
    private String LIBRARY = "linkText=>Library";
    private String CONFERENCES = "linkText=>Conferences";
    private String CONFERENCES_CATEGORIES = "cssSelector=>.dropdown-menu.show > a";
    private String SUMMITS = "linkText=>Summits";
    private String NETWORK = "linkText=>Network";
    private String ROOMS = "linkText=>Rooms";
    private String POP_UP_CLOSE_BTN = "cssSelector=>.introjs-skipbutton";
    private String LOGIN_LINK = "linkText=>LOGIN";
    //    private String POP_BTN = "xpath=>/html/body/div[1]/div/div/button";
    private String POP_BTN = "cssSelector=>.close";
    private String PROFILE = "cssSelector=>button#dropdownMenuButton";
    private String LOGOUT_BTN = "cssSelector=>.logout-btn a";
    private String CREATE_CONFERENCE_LINK = "linkText=>Create Conference";

    public NavigationBar(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public LoginPage clickLoginLink() {
        signUpNotification();
        if (firstTime) {
            clickPopClose(POP_BTN, 30, "Pop close btn");
            elementClick(LOGIN_LINK, "login btn");
            firstTime = false;
        } else {
            elementClick(LOGIN_LINK, "login btn");
        }
        return new LoginPage(driver);
    }

    public boolean isUserLoggedIn() {
        return isElementPresent(PROFILE, "Profile ");
    }

    public void clickLogOut() {
        elementClick(PROFILE, "profile");
        clickWhenReady(LOGOUT_BTN, 30);
    }

    public void cutPopUp() {
        clickWhenReady(POP_UP_CLOSE_BTN, 30);
    }

    public NavigationBar clickConferenceCategory(String categoryType) {
        elementClick(CONFERENCES, "Conference btn");
        List<WebElement> categoryList = getElementList(CONFERENCES_CATEGORIES, "Conferences categories");
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

    public boolean verifyHeader() {
        WebElement link = getElement(CREATE_CONFERENCE_LINK, "create conference link");
        return Util.verifyTextMatch(link.getText(), "Create Conference");
    }

}
