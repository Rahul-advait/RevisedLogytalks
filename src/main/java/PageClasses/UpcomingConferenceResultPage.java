package PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UpcomingConferenceResultPage extends UpcomingConference {
    private String URL = "?category=7";
    private String COURSES_LIST = "xpath=>//div[#'nav-home']/?/?/div[@safeclass~'\\brow\\b']/div";

    public UpcomingConferenceResultPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpen() {
        return driver.getCurrentUrl().contains(URL);
    }

    private int coursesCount() {
        List<WebElement> conferenceList = getElementList(COURSES_LIST, "Courses List");
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
