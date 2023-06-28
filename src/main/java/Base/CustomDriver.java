package Base;

import Utilities.Constants;
import Utilities.Util;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class CustomDriver {
    private static final Logger log = LogManager.getLogger(CustomDriver.class.getName());
    protected WebDriver driver;
    private JavascriptExecutor js;

    public CustomDriver(WebDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor) driver;
    }


    public boolean closeCurrentOpenNew(String page) {
        boolean isNewWindow = false;
        try {
            String originalWindow = driver.getWindowHandle();
            log.info("Originally in " + originalWindow);
            driver.switchTo().newWindow(WindowType.TAB);
//            js.executeScript("window.open();");
            log.info("Now in " + driver.getWindowHandle());
            String newWindow = driver.getWindowHandle();

            driver.switchTo().window(originalWindow);
            log.info("Switched to " + driver.getWindowHandle());
            driver.close();
            log.info("Closed current window");
            driver.switchTo().window(newWindow);
            log.info("Now in " + driver.getWindowHandle());
            driver.get(Constants.BASE_URL + page);
            log.info("Entered this URL : " + Constants.BASE_URL + page);
            isNewWindow = true;
        } catch (Exception e) {
            log.error("Exccption in switching tabs");
            log.error(e.getMessage());
            log.error(e);
            e.printStackTrace();
            isNewWindow = false;
        }
        return isNewWindow;
    }

    public void refresh() {
        driver.navigate().refresh();
        log.info("The current browser location was refreshed");
    }

    public void navigateBrowserBack() {
        driver.navigate().back();
        log.info("Navigate Back");
    }

    public void navigateBrowserForward() {
        driver.navigate().forward();
        log.info("Navigate Forward");
    }

    public String getTitle() {
        String title = driver.getTitle();
        log.info("The title of the page is: " + title);
        return title;
    }

    public By getByType(@NotNull String locator) {
        By by = null;
        String locatorType = locator.split("=>")[0];
        locator = locator.split("=>")[1];
        try {

            if (locatorType.contains("id")) {
                by = By.id(locator);
            } else if (locatorType.contains("name")) {
                by = By.name(locator);
            } else if (locatorType.contains("xpath")) {
                by = By.xpath(locator);
            } else if (locatorType.contains("css")) {
                by = By.cssSelector(locator);
            } else if (locatorType.contains("class")) {
                by = By.className(locator);
            } else if (locatorType.contains("tag")) {
                by = By.tagName(locator);
            } else if (locatorType.contains("link")) {
                by = By.linkText(locator);
            } else if (locatorType.contains("partiallink")) {
                by = By.partialLinkText(locator);
            } else {
                log.info("Locator type not supported");
            }
        } catch (Exception e) {
            log.error("By type not found with : " + locatorType);
        }
        return by;
    }

    public WebElement getElement(String locator, String info) {
        WebElement element = null;
        By byType = getByType(locator);
        try {
            element = driver.findElement(byType);
            log.info(info + ", found  " + info);
        } catch (Exception e) {
            log.error("Element not found " + info);
            e.printStackTrace();
        }
        return element;
    }

    public List<WebElement> getElementList(String locator, String info) {
        List<WebElement> elementList = new ArrayList<WebElement>();
        By byType = getByType(locator);
        try {
            elementList = driver.findElements(byType);
            log.info("Element list found  :" + info);
        } catch (Exception e) {
            log.error("Element list not found  : " + info);
            e.printStackTrace();
        }
        return elementList;
    }

    public boolean isElementPresent(String locator, String info) {
        List<WebElement> elementList = getElementList(locator, info);
        int size = elementList.size();
        if (size > 0) {
            log.info(info + "Element is present");
            return true;
        } else {
            log.info(info + "Element is not present");
            return false;
        }
    }

    public void elementClick(WebElement element, String info, long timeToWait) {
        try {
            element.click();
            if (timeToWait == 0) {
                log.info("Clicked on::" + info);
            } else {
                Util.sleep(timeToWait, "Clicked on ::" + info);
            }
        } catch (Exception e) {
            log.error("Cannot click on ::" + info);
            log.error(e.getMessage());
            e.printStackTrace();
            takeScreenshot("Click ERROR", "");
        }
    }

    public void elementClick(WebElement element, String info) {
        elementClick(element, info, 0);
    }

    public void elementClick(String locator, String info, long timeToWait) {
        WebElement element = getElement(locator, info);
        elementClick(element, info, timeToWait);
    }

    public void elementClick(String locator, String info) {
        WebElement element = getElement(locator, info);
        elementClick(element, info, 0);
    }

    public void javascriptClick(String locator, String info) {
        WebElement element = getElement(locator, info);
        try {
            js.executeScript("arguments[0].click();", element);
            log.info("Clicked on :: " + info);
        } catch (Exception e) {
            log.error("Cannot click on :: " + info);
        }
    }

    public void javascriptScrollToView(String locator, String info) {
        WebElement element = getElement(locator, info);
        try {
            js.executeScript("arguments[0].scrollIntoView();", element);
            log.info("Scrolled on :: " + info);
        } catch (Exception e) {
            log.error("Cannot scrolled on :: " + info);
        }
    }

    public void clickWhenReady(String locator, int timeout, String info) {
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            WebElement element = null;
            element = getElement(locator, info);

            log.info("Waiting for " + timeout + " seconds for element to be clickable: " + info);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(
                    ExpectedConditions.elementToBeClickable(element));
            element.click();
            log.info("Clicked on waited Element");
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Element not appeared on the web page: " + info);
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        }
    }

    public void clickPopClose(String locator, int timeout, String info, String visLocator) {
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            WebElement element = null, show = null;
            element = getElement(locator, info);
            show = getElement(visLocator, "Visiblity locator");
            log.info("Waiting for max:: " + timeout + " seconds for element to be clickable");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout), Duration.ofMillis(3000));
            wait.until(ExpectedConditions.attributeToBe(show
                    , "class", "modal fade show"));
            log.info(show.getAttribute("class"));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();

            log.info("Clicked on close btn");
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Element not appeared on the web page");
            driver.navigate().refresh();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        }
    }


    public void sendData(WebElement element, String data, String info, Boolean clear) {
        try {
            if (clear) {
                element.clear();
            }
//            Util.sleep(1000, "Waiting Before Entering Data");
            element.sendKeys(data);
            log.info("Send Keys on element :: "
                    + info + " with data :: " + data);
        } catch (Exception e) {
            log.error("Cannot send keys on element :: "
                    + info + " with data :: " + data);
        }
    }

    public void sendData(String locator, String data, String info, Boolean clear) {
        WebElement element = this.getElement(locator, info);
        sendData(element, data, info, clear);
    }

    public void sendData(WebElement element, String data, String info) {
        sendData(element, data, info, true);
    }

    public void sendData(String locator, String data, String info) {
        WebElement element = getElement(locator, info);
        sendData(element, data, info, true);
    }

    public String getText(WebElement element, String info) {
        log.info("Getting Text on element :: " + info);
        String text = null;
        text = element.getText();
        if (text.length() == 0) {
            text = element.getAttribute("innerText");
        }
        if (!text.isEmpty()) {
            log.info(" The text is : " + text);
        } else {
            text = "NOT_FOUND";
        }
        return text.trim();
    }

    public String getText(String locator, String info) {
        WebElement element = this.getElement(locator, info);
        return this.getText(element, info);
    }

    public Boolean isEnabled(WebElement element, String info) {
        Boolean enabled = false;
        if (element != null) {
            enabled = element.isEnabled();
            if (enabled)
                log.info("Element :: " + info + " is Enabled");
            else
                log.info("Element :: " + info + " is Disabled");
        }
        return enabled;
    }

    public Boolean isEnabled(String locator, String info) {
        WebElement element = getElement(locator, info);
        return this.isEnabled(element, info);
    }

    public Boolean isDisplayed(WebElement element, String info) {
        Boolean displayed = false;
        if (element != null) {
            displayed = element.isDisplayed();
            if (displayed)
                log.info("Element :: " + info + " is displayed");
            else
                log.info("Element :: " + info + " is NOT displayed");
        }
        return displayed;
    }

    public Boolean isDisplayed(String locator, String info) {
        WebElement element = getElement(locator, info);
        return this.isDisplayed(element, info);
    }

    public Boolean isSelected(WebElement element, String info) {
        Boolean selected = false;
        if (element != null) {
            selected = element.isSelected();
            if (selected)
                log.info("Element :: " + info + " is selected");
            else
                log.info("Element :: " + info + " is not selected");
        }
        return selected;
    }

    public Boolean isSelected(String locator, String info) {
        WebElement element = getElement(locator, info);
        return isSelected(element, info);
    }

    public void Check(WebElement element, String info) {
        if (!isSelected(element, info)) {
            elementClick(element, info);
            log.info("Element :: " + info + " is checked");
        } else
            log.info("Element :: " + info + " is already checked");
    }

    public void Check(String locator, String info) {
        WebElement element = getElement(locator, info);
        Check(element, info);
    }

    public void UnCheck(WebElement element, String info) {
        if (isSelected(element, info)) {
            elementClick(element, info);
            log.info("Element :: " + info + " is unchecked");
        } else
            log.info("Element :: " + info + " is already unchecked");
    }

    public void UnCheck(String locator, String info) {
        WebElement element = getElement(locator, info);
        UnCheck(element, info);
    }

    public Boolean Submit(WebElement element, String info) {
        if (element != null) {
            element.submit();
            log.info("Element :: " + info + " is submitted");
            return true;
        } else
            return false;
    }

    public String getElementAttributeValue(String locator, String attribute) {
        WebElement element = waitForElement(locator, 20, "info");
//        WebElement element = getElement(locator, "info");
        return element.getAttribute(attribute);
    }

    public String getElementAttributeValue(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }

    public WebElement waitForElement(String locator, int timeout, String info) {
        By byType = getByType(locator);
        WebElement element = null;
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            log.info("Waiting for max:: " + timeout + " seconds for element to be available");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(byType));
            log.info("Element appeared on the web page " + info);
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Element not appeared on the web page " + info);
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        }
        return element;
    }

    public void waitThenSendData(String locator, int timeout, String data, String info) {
        WebElement element = waitForElement(locator, timeout, info);
        sendData(element, data, info);
    }

    public WebElement waitThenClick(String locator, int timeout, String info) {
        By byType = getByType(locator);
        WebElement element = null;
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            log.info("Waiting for max:: " + timeout + " seconds for element to be clickable");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            element = wait.until(
                    ExpectedConditions.elementToBeClickable(byType));
            log.info("Element is clickable on the web page");
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            elementClick(element, info);
        } catch (Exception e) {
            log.error("Element not appeared on the web page");
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        }
        return element;
    }

    public void mouseHover(String locator, String info) {
        WebElement element = getElement(locator, info);
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
        //Util.sleep(5000);
    }

    public void selectOption(WebElement element, String optionToSelect) {
        Select sel = new Select(element);
        sel.selectByVisibleText(optionToSelect);
        log.info("Selected option : " + optionToSelect);
    }

    public void selectOption(String locator, String optionToSelect, String info) {
        WebElement element = getElement(locator, info);
        this.selectOption(element, optionToSelect);
    }

    public String getSelectDropDownValue(WebElement element) {
        Select sel = new Select(element);
        return sel.getFirstSelectedOption().getText();
    }

    public boolean isOptionExists(WebElement element, String optionToVerify) {
        Select sel = new Select(element);
        boolean exists = false;
        List<WebElement> optList = sel.getOptions();
        for (int i = 0; i < optList.size(); i++) {
            String text = getText(optList.get(i), "Option Text");
            if (text.matches(optionToVerify)) {
                exists = true;
                break;
            }
        }
        if (exists) {
            log.info("Selected Option : " + optionToVerify + " exist");
        } else {
            log.info("Selected Option : " + optionToVerify + " does not exist");
        }
        return exists;
    }

    public String takeScreenshot(String methodName, String browserName) {
        String fileName = Util.getScreenshotName(methodName, browserName);
        String screenshotDir = System.getProperty("user.dir") + "//" + "test-output/screenshots";
        new File(screenshotDir).mkdirs();
        String path = screenshotDir + "//" + fileName;

        try {
            File screenshot = ((TakesScreenshot) driver).
                    getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(path));
            log.info("Screen Shot Was Stored at: " + path);
        } catch (Exception e) {
            log.error("Esception ocurred while taking screenshot");
            e.printStackTrace();
        }
        return path;
    }

    public void DoubleClick(WebElement element, String info) {
        Actions action = new Actions(driver);
        action.doubleClick(element);
        log.info("Double Clicked on :: " + info);
        action.perform();
    }

    public void checkPageLinks(String currentUrl) {
        List<WebElement> links = null;
        String url = "", linkText = "";
        try {
            links = getElementList("tagName=>a", "a links");
            for (WebElement link : links) {
                url = link.getAttribute("href");
                linkText = link.getText();
                if (url == null || url.isEmpty()) {
                    log.error("URL is null: " + url + " " + linkText);
                    continue;
                }
                if (!url.startsWith(currentUrl)) {
                    log.error("URL belongs to another domain: " + url + " " + linkText);
                    continue;
                }
                checkResponseCode(url, linkText);
            }
        } catch (Exception e) {
            log.error("Exception ocurred while getting all page links");
        }
    }

    public void checkResponseCode(String url, String linkText) {
        HttpURLConnection huc;
        int responseCode = 200;
        try {
            huc = (HttpURLConnection) (new URL(url).openConnection());
            huc.setRequestMethod("HEAD");
            huc.connect();
            responseCode = huc.getResponseCode();
            if (responseCode >= 400) {
                log.error("The URL " + url + " is a broken link. Response code: "
                        + responseCode + " " + linkText);
            } else {
                log.info("The URL " + url + " is a valid link. Response code: "
                        + responseCode + " " + linkText);
            }
        } catch (Exception e) {
            log.error("Exception occured while getting connecting with url");
        }
    }

}
