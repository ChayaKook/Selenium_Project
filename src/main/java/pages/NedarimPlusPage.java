package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class NedarimPlusPage {

    public boolean isOnOpen(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try{
            wait.until(ExpectedConditions.visibilityOf(getLogo()));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public NedarimPlusPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    WebDriver driver;

    @FindBy(xpath="html/body/div/div/img")
    private WebElement logo;

    @FindBy(xpath="html/body/div/div/ul/li/a")
    private WebElement findButton;




}
