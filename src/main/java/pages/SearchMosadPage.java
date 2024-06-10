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
public class SearchMosadPage {
    public boolean isOnOpen(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try{
            wait.until(ExpectedConditions.visibilityOf(getInput()));
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public SearchMosadPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    WebDriver driver;

    @FindBy(id="SearchByNameTxt")
    private WebElement input;
    @FindBy(id="SearchBt")
    private WebElement button;
    @FindBy(id="SearchResult")
    private WebElement results;

    @FindBy(id="ClientBox1")
    private WebElement yadSaraResults;


}
