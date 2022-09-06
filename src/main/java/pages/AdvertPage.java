package pages;

import model.Advert;
import model.WriteReadCSV;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.log4testng.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdvertPage extends BasePage {
    final static Logger logger = Logger.getLogger(AdvertPage.class);

    private final String pathSavePhoto = "src/main/resources/photo/";

    private final By TITLE = By.cssSelector("div[class='gt60zsk1 rl78xhln r227ecj6 g4qalytl'] span[class^='gvxzyvdx aeinzg81 t7p7dqev gh25dzvf ocv3nf92 b6ax4al1 gem102v4 ncib64c9 mrvwc6qr sx8pxkcf f597kf1v cpcgwwas m2nijcs8 pc9ouhwb qntmu8s7 tq4zoyjo o48pnaf2 pbevjfx6']");
    private final By PRICE = By.cssSelector("div[class^='gt60zsk1 rl78xhln r227ecj6 g4qalytl'] span[class^='gvxzyvdx aeinzg81 t7p7dqev gh25dzvf ocv3nf92 b6ax4al1 gem102v4 ncib64c9 mrvwc6qr sx8pxkcf f597kf1v cpcgwwas pk1vzqw1 ']");
    private final By DESCRIPTION = By.cssSelector("div[class='n3t5jt4f nch0832m rj2hsocd oxkhqvkx s1m0hq7j'] span[class='gvxzyvdx aeinzg81 t7p7dqev gh25dzvf ocv3nf92 b6ax4al1 gem102v4 ncib64c9 mrvwc6qr sx8pxkcf f597kf1v cpcgwwas f5mw3jnl ib8x7mpr k1z55t6l oog5qr5w tes86rjd pbevjfx6']");
    private final By GEOLOCATION = By.xpath("//div[contains(@style,'language=')]");
    private final By LIST_BUTTON_FOTO = By.cssSelector("img[class='pytsy3co p9wrh9lq mfclru0v']");
    private final By PHOTO = By.cssSelector("div[class^='i85zmo3j alzwoclg cgu29s5g'] img");
    private final By EXPAND_DESCRIPTION = By.cssSelector("span[class='gvxzyvdx aeinzg81 t7p7dqev gh25dzvf ocv3nf92 k1z55t6l oog5qr5w innypi6y pbevjfx6']");

    public AdvertPage(WebDriver driver) {
        super(driver);
    }


    public void clickExpandDescription() {
        try {
            driver.findElement(EXPAND_DESCRIPTION).click();
            logger.debug("click button expand_description");
            System.out.println("click");
        } catch (Exception e) {
            logger.debug("no click button expand_description");
        }
    }

    public void getTitle(Advert advert) {
        advert.setTitle(driver.findElement(TITLE).getText());
    }

    public void getDescription(Advert advert) {
        clickExpandDescription();
        advert.setDescription(driver.findElement(DESCRIPTION).getText());
    }

    public void getPrice(Advert advert) {
        advert.setPrice(driver.findElement(PRICE).getText());
    }

    public void getGeolocation(Advert advert) {
        String textGeolocationRaw = driver.findElement(GEOLOCATION).getAttribute("style");
        logger.debug("Geolocation raw:"+ textGeolocationRaw);
        Pattern pattern = Pattern.compile("center=(\\d+\\.\\d+)%2C(\\d+\\.\\d+)");
        Matcher matcher = pattern.matcher(textGeolocationRaw);
        matcher.find();
        String geolocation_x = matcher.group(1);
        String geolocation_y = matcher.group(2);
        advert.setGeolocation(geolocation_x, geolocation_y);
    }

    public List<WebElement> getListPhoto() {
        return driver.findElements(LIST_BUTTON_FOTO);
    }

    public boolean saveImage(Advert advert, int number_photo) {
        WebElement image = driver.findElement(PHOTO);
        String src = image.getAttribute("src");
        try {
            BufferedImage bufferedImage = ImageIO.read(new URL(src));
            File outfile = new File(pathSavePhoto  + advert.getId() + " " + number_photo + ".jpg");
//            ImageIO.write(bufferedImage, "jpg", outfile);
            advert.addPhoto(outfile);
            logger.debug("save image");
            return true;
        } catch (Exception e) {
            logger.error("image not save");
            return false;
        }
    }

    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd-HH--mm-ss-ms");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
}
