import io.github.bonigarcia.wdm.WebDriverManager;
import model.Advert;
import model.WriteReadCSV;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.BusinessLogic;

import java.util.ArrayList;
import java.util.List;


public class Main {
    private static final String URL = "https://www.facebook.com/marketplace/115427551801302/propertyrentals?exact=false&latitude=9.5365&longitude=100.0564&radius=8";

    static WebDriver driver;


    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.addArguments("--window-size=1920,1080", "--log-level=3");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        driver.get(URL);
        int numberScroll = 6;
        List<Advert> adverts = BusinessLogic.getListAdvert(numberScroll, driver);
        List<String> oldId = WriteReadCSV.readCsvFile();
        List<String> newId = new ArrayList<>();
        for (Advert i : adverts) {
            if (!oldId.contains(i.getId())){
                String href = i.getHref();
                newId.add(i.getId());
                driver.get(href);
                BusinessLogic.saveAdvert(href, driver);
            }
            WriteReadCSV.writeDataAtOnce(newId);
        }
        driver.close();
    }

}
