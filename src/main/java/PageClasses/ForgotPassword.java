package PageClasses;

import Base.BasePage;
import org.openqa.selenium.WebDriver;

public class ForgotPassword extends BasePage {
    protected WebDriver driver;
    private String URL = "/password/reset";
    public ForgotPassword(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
   public boolean isOpen(){
        return super.isOpen(URL);
   }
}
