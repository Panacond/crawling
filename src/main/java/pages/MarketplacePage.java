package pages;

import model.Advert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MarketplacePage extends BasePage {

    public final By LIST_AD_XPATH = By.xpath("//a[contains(@href,'/marketplace/item/')]");
    public List<Advert> adverts = new ArrayList();
    public Advert advert;
    private String href;


    public MarketplacePage(WebDriver driver) {
        super(driver);
    }

    public List<Advert> getListAdverts(){
        /** create AdvertPage with href and id
        * */
         List<WebElement> listWebElement = driver.findElements(LIST_AD_XPATH);
         for(int i=0; i< listWebElement.size();i++){
             href = listWebElement.get(i).getAttribute("href");
             advert = new Advert(href);
             adverts.add(advert);
         }
        return adverts;
    }

    public void scrollDown(int numberScroll){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        if (numberScroll<1) numberScroll = 1;
        int scrollScreen = numberScroll*1080;

        String scrollTextCommand = "window.scrollBy(0," + String.valueOf(scrollScreen) + ")";
        js.executeScript(scrollTextCommand, "");
    }

    public void waitPastElement(long timeToWait, int numberScroll){
        int lenElements = 0;
        while (lenElements < numberScroll*3) {
            List<WebElement> listWebElement = driver.findElements(LIST_AD_XPATH);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeToWait));
            wait.until(ExpectedConditions.visibilityOf(listWebElement.get(listWebElement.size() - 1)));
            lenElements = listWebElement.size();
        }
    }

}
