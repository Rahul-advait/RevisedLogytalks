package PageClasses;

import Utilities.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class UpcomingConference extends NavigationBar {

    protected WebDriver driver;
    private String UPCOMING_DROPDOWN = "cssSelector=>.form-control";
    private String URL = Constants.BASE_URL + "/upcoming-conferences";
    private String SHARE_YOUR_KNOWLEDGE_LINK = "cssSelector=>[data-aos='zoom-in']";


    public UpcomingConference(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


    public String getURL() {
        return URL;
    }

    public boolean isOpen() {
        return URL.equalsIgnoreCase(driver.getCurrentUrl());
    }

    public UpcomingConferenceResultPage filter(String category) {
        Select selectCourse = new Select(getElement(UPCOMING_DROPDOWN, "conference category"));
        selectCourse.selectByVisibleText(category);

        return new UpcomingConferenceResultPage(driver);
    }

    public HomePage clickShareYourKnowledgeLink() {
        javascriptScrollToView(SHARE_YOUR_KNOWLEDGE_LINK, "Share you knowledge btn");
        clickWhenReady(SHARE_YOUR_KNOWLEDGE_LINK, 20);
        return new HomePage(driver);
    }
}

