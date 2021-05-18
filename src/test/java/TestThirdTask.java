import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TestThirdTask {

    public static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(TestThirdTask.class);


    By electroinst = By.xpath("//a[@title='Электроинструменты']");
    By perforator = By.xpath(".//span[text()=\"Перфораторы\"]");
    By checkBoxMakita = By.xpath("//input[@title ='MAKITA']");
    By checkBoxZubr = By.xpath("//input[@title ='ЗУБР']");
    By filterSubmit = By.id("filterSubm");
    By dropDownArrow = By.className("select2-selection__arrow");
    By priceFilter = By.xpath(".//span[text()=\"Цене\"]");
    By matchZubr = By.xpath("//i[@title ='Добавить к сравнению']");
    By findMakita = By.xpath("//a[contains(@title,\"Перфоратор MAKITA\")]");
    By addMataToMatch = By.xpath("//label[@for='compare-691618']");
    By activeButton = By.xpath("//button[@title ='Close']");
    By math = By.className("linkContinueView");
    By resultZubr = By.xpath("//div[@class=\"title\"]//a");
    By resultMakita = By.xpath("//*[@id=\"691618_compare\"]/div[2]//a");




    @BeforeAll
    static void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();}

    @Test
    @DisplayName("compare Makita and Zubr")
    public void instrumentComparison() throws InterruptedException {
        driver.get("https://www.220-volt.ru/");
        logger.info("The site is opened");

       getWebElement(electroinst).click();
       getWebElement(perforator).click();
       logger.info("Chosen instruments");
       getWebElement(checkBoxMakita).click();
       getWebElement(checkBoxZubr).click();
       logger.info("Added Makita and Zubr");
       getWebElement(filterSubmit).click();
       getWebElement(dropDownArrow).click();

       getWebElement(priceFilter).click();
       logger.info("Price filter used from min > max");
       getWebElement(matchZubr).click();
       getWebElement(activeButton).click();
       driver.navigate().refresh();
       getWebElement(findMakita).findElement(addMataToMatch).click();
       getWebElement(math).click();
       logger.info("The instruments added to the compare ");

        Thread.sleep(5000);
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        logger.info("Switched to the new window");
        String text =  driver.findElement(resultZubr).getText();
        String text2 =  driver.findElement(resultMakita).getText();
        Assertions.assertEquals("ЗУБР ЗП-18-470", text);
        Assertions.assertEquals("MAKITA M8701", text2);

    }

    private WebElement getWebElement(By locator) {
        return new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @AfterAll
    static void tearDown(){
        if (driver !=null){
            driver.quit();

        }

    }
}
