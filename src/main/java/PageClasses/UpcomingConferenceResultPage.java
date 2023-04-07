package PageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class UpcomingConferenceResultPage {
    private WebDriver driver;
    private String URL = "?category=7";
    private String SHARE_YOUR_KNOWLEDGE_LINK = "[data-aos='zoom-in']";
    private String COURSES_LIST = "//div[#'nav-home']/?/?/div[@safeclass~'\\brow\\b']/div";

    public UpcomingConferenceResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isOpen() {
        return driver.getCurrentUrl().contains(URL);
    }

    public int coursesCount() {
        List<WebElement> conferenceList = driver.findElements(By.xpath(COURSES_LIST));
        return conferenceList.size();
    }

    public boolean verifySearchResult() {
        boolean result = false;
        if (coursesCount() > 0) {
            result = true;
        }
        result = isOpen() && result;
        return result;
    }

    public HomePage clickShareYourKnowledgeLink() {
        WebElement button = driver.findElement(By.cssSelector(SHARE_YOUR_KNOWLEDGE_LINK));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].scrollIntoView();", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(button));

        button.click();
        return new HomePage(driver);
    }
}
