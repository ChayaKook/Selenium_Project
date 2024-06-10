package pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class YadSaraPage {
    public boolean isOnOpen(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try{
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("WelcomLogo"))));
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public YadSaraPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    WebDriver driver;

    @FindBy(id="ClientNameTxt")
    private WebElement name;
    @FindBy(id="ZeoutTxt")
    private WebElement id;
    @FindBy(id="AdresseTxt")
    private WebElement address;
    @FindBy(id="CityTxt")
    private WebElement city;
    @FindBy(id="PhoneTxt")
    private WebElement phone;
    @FindBy(id="MailTxt")
    private WebElement mail;
    @FindBy(id="AvourTxt")
    private WebElement notice;
    @FindBy(id="CreditCardRagilDiv")
    private WebElement paymentOptions;

    @FindBy(id="AmountTxt")
    private WebElement sum;
    @FindBy(id="TashloumimTxt")
    private WebElement numberPayments;
    @FindBy(id="CardTxt")
    private WebElement numberCard;
    @FindBy(id="TokefTxt")
    private WebElement validityCard;
    @FindBy(id="CvvTxt")
    private WebElement cvvCard;
    @FindBy(id="TakanonCb")
    private WebElement IAgree;

    @FindBy(id="TashloumimAlertTxt")
    private WebElement NumberPaymentsAlert;

    @FindBy(id="PayBtBt")
    private WebElement paymentSubmit;

    public boolean findAlert(){
        try{
            paymentApprovalAlert= driver.findElement(By.className("sweet-alert"));
            paymentApprovalButton=driver.findElement(By.xpath(paymentApprovalAlert+"/p/span"));
//            PaymentApprovalText=driver.findElement(By.className("confirm"));
            return  true;
        }catch (Exception e){
            return  false;
        }
    }

    private WebElement paymentApprovalAlert;
//    @FindBy(className="confirm")
    private WebElement paymentApprovalButton;
//    @FindBy(className="confirm")
    private WebElement PaymentApprovalText;



}
