package nedarim_plus;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.NedarimPlusPage;
import pages.SearchMosadPage;
import pages.YadSaraPage;

import java.time.Duration;
import java.util.Set;


public class NedarimTest {

    public WebDriver eDriver = new EdgeDriver();

    @BeforeEach
    public void beforeTest(){
        eDriver.get("https://www.matara.pro/nedarimplus/");
    }
    @AfterEach
    public void afterTest(){
        eDriver.quit();
    }
    @Test
    public void yadSaraTest() throws InterruptedException {

        //nedarim site
        NedarimPlusPage npp = new NedarimPlusPage(eDriver);
        Assert.assertTrue(npp.isOnOpen());
        npp.getFindButton().click();

        //nedarim search
        SearchMosadPage smp = new SearchMosadPage(eDriver);
        Assert.assertTrue(smp.isOnOpen());
        smp.getInput().sendKeys("hello world:)))");
        smp.getButton().click();
        Thread.sleep(2500);

        Assert.assertTrue(smp.getResults().getText().equals("אין תוצאות"));

        smp.getInput().clear();
        smp.getInput().sendKeys("יד שרה");
        smp.getButton().click();
        Assert.assertFalse(smp.getResults().getText().equals("אין תוצאות"));

        String parentWindow = eDriver.getWindowHandle();

        WebDriverWait wait = new WebDriverWait(eDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(smp.getYadSaraResults())).click();

        System.out.println(eDriver.getCurrentUrl());
        Set<String> windows = eDriver.getWindowHandles();
        for (String window : windows)
            if (!parentWindow.equalsIgnoreCase(window))
                eDriver.switchTo().window(window);
        System.out.println(eDriver.getCurrentUrl());

        //yad sara
        YadSaraPage ysp = new YadSaraPage(eDriver);
        Assert.assertTrue(ysp.isOnOpen());

        ysp.getId().sendKeys("321654987");
        ysp.getCity().sendKeys("modiinIllit");
        ysp.getAddress().sendKeys("Haritva6");
        ysp.getMail().sendKeys("a@mail");
        ysp.getName().sendKeys("chaya");
        ysp.getPhone().sendKeys("0548475632");
        ysp.getNotice().sendKeys("Welcome to Selenium's auto-check");
        ysp.getPaymentOptions().click();

        ysp.getSum().sendKeys("100");
        ysp.getNumberPayments().clear();
        ysp.getNumberPayments().sendKeys("2");
        Assert.assertTrue(ysp.getNumberPaymentsAlert().getText().equals("כל תשלום ע\"ס: 50.00 ₪"));

        ysp.getNumberPayments().clear();
        ysp.getNumberPayments().sendKeys("3");
        Assert.assertTrue(ysp.getNumberPaymentsAlert().getText().equals("כל תשלום ע\"ס: 33.33 ₪"));

        ysp.getNumberPayments().clear();
        ysp.getNumberPayments().sendKeys("1");
        Assert.assertTrue(ysp.getNumberPaymentsAlert().getText().equals(""));

        ysp.getNumberCard().sendKeys("1234567894561312");
        ysp.getCvvCard().sendKeys("456");
        ysp.getValidityCard().sendKeys("1026");

        Thread.sleep(1000);
        ysp.getIAgree().click();
        Thread.sleep(2500);
        ysp.getPaymentSubmit().click();

        Assert.assertTrue(ysp.findAlert());
        System.out.println(ysp.getPaymentApprovalText().getText());
        Assert.assertTrue(ysp.getPaymentApprovalText().getText().equals("נא לאשר חיוב על סך 100 ₪"));
        Thread.sleep(2000);
        ysp.getPaymentApprovalButton().click();
        Thread.sleep(2000);
        System.out.println(ysp.getPaymentApprovalError().getText());
        Thread.sleep(1000);
        Assert.assertTrue(ysp.getPaymentApprovalError().getText().equals("מספר כרטיס אשראי שגוי. טעות בהקלדה."));
        ysp.getPaymentApprovalButton().click();
        Assert.assertTrue(ysp.isOnOpen());
        Thread.sleep(1000);

    }
}
