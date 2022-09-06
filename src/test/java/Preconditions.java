import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public abstract class Preconditions {
    protected WebDriver driver;
    private final String URL = "https://www.facebook.com/marketplace/115427551801302/propertyrentals?exact=false&latitude=9.5365&longitude=100.0564&radius=8";

    @BeforeTest
    public void profileSetUp(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void testsSetUp() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.addArguments("--window-size=1920,1080", "--log-level=3");
        driver = new ChromeDriver(options);
//        VideoRecorder_utlity.startRecord("GoogleTestRecording");
        driver.manage().window().maximize();
//        driver.get(URL);
    }

    @AfterMethod
    public void tearDown() throws Exception {
//        VideoRecorder_utlity.stopRecord();
        driver.close();
    }

}
