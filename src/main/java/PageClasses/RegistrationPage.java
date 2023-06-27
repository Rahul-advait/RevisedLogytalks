package PageClasses;

import Base.BasePage;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage {
    protected WebDriver driver;
    private String URL = "register";
    public RegistrationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public boolean isOpen(){
        return super.isOpen(URL);
    }
}
