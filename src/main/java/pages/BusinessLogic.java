package pages;

import model.Advert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.log4testng.Logger;

import java.util.List;

public class BusinessLogic {
    final static Logger logger = Logger.getLogger(BusinessLogic.class);


    public static List<Advert> getListAdvert(int  numberScroll, WebDriver driver){
        MarketplacePage marketplacePage = new MarketplacePage(driver);
        marketplacePage.implicitWait(10);
        marketplacePage.waitVisibilityOfElement(10, driver.findElement(marketplacePage.LIST_AD_XPATH));
        marketplacePage.scrollDown(numberScroll);
        marketplacePage.implicitWait(10);
        marketplacePage.waitVisibilityOfElement(10, driver.findElement(marketplacePage.LIST_AD_XPATH));
        marketplacePage.waitPastElement(10, numberScroll);
        return marketplacePage.getListAdverts();
    }

    public static void saveAdvert(String URL, WebDriver driver){
        Advert advert = new Advert(URL);
        AdvertPage advertPage = new AdvertPage(driver);
        advertPage.getTitle(advert);
        advertPage.getPrice(advert);
        advertPage.getDescription(advert);
        advertPage.getGeolocation(advert);
        List<WebElement> listPhoto = advertPage.getListPhoto();
        for(int i = 1; i < listPhoto.size(); i++){
            advertPage.implicitWait(1);
            try{
                advertPage.waitClickOfElement(1,listPhoto.get(i));
                listPhoto.get(i).click();
                advertPage.saveImage(advert, i);
            }catch (Exception e){
                logger.error("no save photo" + i);
            }

        }
        logger.debug(advert);
    }
}
