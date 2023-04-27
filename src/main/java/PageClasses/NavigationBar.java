package PageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class NavigationBar {
    private WebDriver driver;
    private String LIBRARY = "Library";
    private String CONFERENCES = "Conferences";
    private String CONFERENCES_CATEGORIES = ".dropdown-menu.show > a";
    private String SUMMITS = "Summits";
    private String NETWORK = "Network";
    private String ROOMS = "Rooms";
    private String POP_UP_CLOSE_BTN = ".introjs-skipbutton";
    private String LOGIN_LINK = "LOGIN";
    private String POP_BTN = "/html/body/div[1]/div/div/button";
    private String PROFILE = "button#dropdownMenuButton";
    private String LOGOUT_BTN = ".logout-btn a";

    public NavigationBar(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public LoginPage clickLogin() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(POP_BTN))));
            driver.findElement(By.xpath(POP_BTN)).click();
            driver.findElement(By.linkText(LOGIN_LINK)).click();
        } catch (Exception e) {
            driver.findElement(By.linkText(LOGIN_LINK)).click();
        }
        return new LoginPage(driver);
    }

    public boolean isUserLoggedIn() {
        try {
            WebElement profileImage = driver.findElement(By.cssSelector(PROFILE));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickLogOut() {
        driver.findElement(By.cssSelector(PROFILE)).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(LOGOUT_BTN))).click();

    }

    public void cutPopUp() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(POP_UP_CLOSE_BTN)));
        driver.findElement(By.cssSelector(POP_UP_CLOSE_BTN)).click();
    }

    public NavigationBar clickConferenceCategory(String categoryType) {
        WebElement conferencesBtn = driver.findElement(By.linkText(CONFERENCES));
        conferencesBtn.click();
        List<WebElement> categoryList = driver.findElements(By.cssSelector(CONFERENCES_CATEGORIES));
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
