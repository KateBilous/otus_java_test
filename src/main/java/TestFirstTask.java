import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestFirstTask {

    protected static WebDriver webDriver;
    private static final Logger logger = LogManager.getLogger(TestFirstTask.class);

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        logger.info("Set up Driver");
    }
    @After
    public void tearDown(){
        if (webDriver != null) {
            webDriver.quit();
        }
        logger.info("Browser shut down");
    }

    @Test
    public void openSite() {
        webDriver.get("https://otus.ru/");
        logger.info("Site is open");
        String actual = webDriver.getTitle();
        Assert.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям", actual);
    }
}
