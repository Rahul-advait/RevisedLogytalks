package PageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UpcomingConferenceResultPage extends UpcomingConference {
    private String URL = "?category=7";
    private String COURSES_LIST = "//div[#'nav-home']/?/?/div[@safeclass~'\\brow\\b']/div";

    public UpcomingConferenceResultPage(WebDriver driver) {
        super(driver);
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


}
