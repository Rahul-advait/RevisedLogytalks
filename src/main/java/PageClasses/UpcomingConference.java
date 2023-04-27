package PageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UpcomingConference extends NavigationBar{

    protected WebDriver driver;
    private String UPCOMING_DROPDOWN = ".form-control";
    private String URL = "https://logytalks.com/upcoming-conferences";
    private String SHARE_YOUR_KNOWLEDGE_LINK = "[data-aos='zoom-in']";


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
        Select selectCourse = new Select(driver.findElement(By.cssSelector(UPCOMING_DROPDOWN)));
        selectCourse.selectByVisibleText(category);

        return new UpcomingConferenceResultPage(driver);
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

