import model.Advert;
import model.WriteReadCSV;
import org.testng.annotations.Test;
import pages.AdvertPage;
import pages.BusinessLogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class TestLoad extends Preconditions {
    private final String URL = "https://www.facebook.com/marketplace/115427551801302/propertyrentals?exact=false&latitude=9.5365&longitude=100.0564&radius=8";

    @Test
    public void testMarketplace() {
    /** work in head mode
     * only ls have recorder from headless mode
     */
// TODO logger
        driver.get(URL);
        int numberScroll = 6;
        List<Advert> adverts = BusinessLogic.getListAdvert(numberScroll, driver);
        System.out.println(adverts.size());
        assertTrue(numberScroll *   4 < adverts.size());
    }

    @Test
    public void testOneScroll() {
        driver.get(URL);
        int numberScroll = 1;
        List<Advert> adverts = BusinessLogic.getListAdvert(numberScroll, driver);
        System.out.println(adverts.size());
        assertTrue(numberScroll * 4 < adverts.size());
    }

    @Test
    public void testSaveImage() {
        String urlPhoto = "https://www.facebook.com/marketplace/item/1057352694977619/?ref=category_feed&referral_code=undefined&referral_story_type=listing&tracking=%7B%22qid%22%3A%22-5851092768330236868%22%2C%22mf_story_key%22%3A%225505151572870303%22%2C%22commerce_rank_obj%22%3A%22%7B%5C%22target_id%5C%22%3A5505151572870303%2C%5C%22target_type%5C%22%3A0%2C%5C%22primary_position%5C%22%3A0%2C%5C%22ranking_signature%5C%22%3A4738263029672574976%2C%5C%22commerce_channel%5C%22%3A504%2C%5C%22value%5C%22%3A4.107008679462e-5%2C%5C%22candidate_retrieval_source_map%5C%22%3A%7B%5C%225505151572870303%5C%22%3A204%7D%7D%22%2C%22ftmd_400706%22%3A%22111112l%22%7D";
        driver.get(urlPhoto);
        AdvertPage advertPage = new AdvertPage(driver);
        advertPage.implicitWait(10);
        Advert advert = new Advert(urlPhoto);
        assertTrue(advertPage.saveImage(advert, 0));
    }

    @Test
    public void testOneAdvert() {
        String URL = "https://www.facebook.com/marketplace/item/1057352694977619/?ref=category_feed&referral_code=undefined&referral_story_type=listing&tracking=%7B%22qid%22%3A%22-5851092768330236868%22%2C%22mf_story_key%22%3A%225505151572870303%22%2C%22commerce_rank_obj%22%3A%22%7B%5C%22target_id%5C%22%3A5505151572870303%2C%5C%22target_type%5C%22%3A0%2C%5C%22primary_position%5C%22%3A0%2C%5C%22ranking_signature%5C%22%3A4738263029672574976%2C%5C%22commerce_channel%5C%22%3A504%2C%5C%22value%5C%22%3A4.107008679462e-5%2C%5C%22candidate_retrieval_source_map%5C%22%3A%7B%5C%225505151572870303%5C%22%3A204%7D%7D%22%2C%22ftmd_400706%22%3A%22111112l%22%7D";
        driver.get(URL);
        BusinessLogic.saveAdvert(URL, driver);
    }

    @Test
    public void testSomeAdvert() {
        List<String> listAdverts = Arrays.asList(
                "https://www.facebook.com/marketplace/item/4815871325181631/?ref=category_feed&referral_code=undefined&referral_story_type=listing&tracking=%7B%22qid%22%3A%22-5831210506471146067%22%2C%22mf_story_key%22%3A%225214298298686891%22%2C%22commerce_rank_obj%22%3A%22%7B%5C%22target_id%5C%22%3A5214298298686891%2C%5C%22target_type%5C%22%3A0%2C%5C%22primary_position%5C%22%3A0%2C%5C%22ranking_signature%5C%22%3A6627089371851915264%2C%5C%22commerce_channel%5C%22%3A504%2C%5C%22value%5C%22%3A0.00012758308408538%2C%5C%22candidate_retrieval_source_map%5C%22%3A%7B%5C%225214298298686891%5C%22%3A204%7D%7D%22%2C%22ftmd_400706%22%3A%22111112l%22%7D",
                "https://www.facebook.com/marketplace/item/1340007599859191/?ref=category_feed&referral_code=undefined&referral_story_type=listing&tracking=%7B%22qid%22%3A%22-5827301054799771986%22%2C%22mf_story_key%22%3A%225190080674412303%22%2C%22commerce_rank_obj%22%3A%22%7B%5C%22target_id%5C%22%3A5190080674412303%2C%5C%22target_type%5C%22%3A0%2C%5C%22primary_position%5C%22%3A0%2C%5C%22ranking_signature%5C%22%3A464698985932652544%2C%5C%22commerce_channel%5C%22%3A504%2C%5C%22value%5C%22%3A0.00014894893571871%2C%5C%22candidate_retrieval_source_map%5C%22%3A%7B%5C%225190080674412303%5C%22%3A204%7D%7D%22%2C%22ftmd_400706%22%3A%22111112l%22%7D",
                "https://www.facebook.com/marketplace/item/573280037650828/?ref=category_feed&referral_code=undefined&referral_story_type=listing&tracking=%7B%22qid%22%3A%22-5826938078533048452%22%2C%22mf_story_key%22%3A%227630136843727952%22%2C%22commerce_rank_obj%22%3A%22%7B%5C%22target_id%5C%22%3A7630136843727952%2C%5C%22target_type%5C%22%3A0%2C%5C%22primary_position%5C%22%3A12%2C%5C%22ranking_signature%5C%22%3A5589040147715850240%2C%5C%22commerce_channel%5C%22%3A504%2C%5C%22value%5C%22%3A0.00011617604673919%2C%5C%22candidate_retrieval_source_map%5C%22%3A%7B%5C%227630136843727952%5C%22%3A204%7D%7D%22%2C%22ftmd_400706%22%3A%22111112l%22%7D"
        );
        for (String i : listAdverts) {
            driver.get(i);
            BusinessLogic.saveAdvert(i, driver);
        }
    }

    @Test
    public void testCrawling(){
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

    }

}
