import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TestSecondTask {

    public static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(TestFirstTask.class);

    @BeforeAll
    static void setUp() {
        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new FirefoxDriver(options);


    }

    @Test
    @DisplayName("Check address on Otus")
    public void checkoutAddressTest() {
        driver.get("https://otus.ru ");
        logger.info("Opened site OTUS");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        By button = By.xpath("//*[@title=\"Контакты\"]");
        By address = By.xpath("/descendant::div[@class= 'c0qfa0-5 cXQVNI'][3]");


        driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
        getWebElement(button).click();
        driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);

        String getAddress = getWebElement(address).getText();
        logger.info("Checked address");
        Assertions.assertEquals("125167, г. Москва, Нарышкинская аллея., д. 5, стр. 2, тел. +7 499 938-92-02",
                getAddress);


    }

    @Test
    @DisplayName("Check title on tele2")
    public void checkTitleTele2() {

        driver.get("https://msk.tele2.ru/shop/number");
        logger.info("Opened site TELE2");
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        logger.info("Checked title");
        String title = driver.getTitle();
        Assertions.assertEquals("Красивые номера - купить красивый федеральный номер телефона Tele2 Москва" +
                " и Московская область, продажа красивых мобильных номеров", title);


    }

    @Test
    @DisplayName("Check phone numbers")
    public void tele2Test() {
        driver.get("https://msk.tele2.ru/shop/number");
        logger.info("Opened TELE2");

        By search = By.id("searchNumber");
        By phoneBlock = By.xpath("//div[@class='catalog-numbers with-overlay overlay-big']");
        getWebElement(search).click();
        getWebElement(search).clear();
        getWebElement(search).sendKeys("97");
        WebElement element = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(phoneBlock));

    }

    @Test
    @DisplayName("Assert test on FAQ")
    public void checkFAQTest() {
        driver.get("https://otus.ru");
        logger.info("Opened OTUS");
        By buttonFAQ = By.xpath("//a[@title='FAQ']");
        By buttonQuestion = By.xpath("(//div[contains(@class, 'faq-question__question')])[4]");
        By buttonAnswer = By.xpath("(//div[contains(@class, 'faq-question__answer')])[4]");


        getWebElement(buttonFAQ).click();
        getWebElement(buttonQuestion).click();
        logger.info("Checked FAQ");
        String getText = getWebElement(buttonAnswer).getText();
        Assertions.assertEquals("Программу курса в сжатом виде можно увидеть на странице курса после блока с преподавателями." +
                " Подробную программу курса можно скачать кликнув на “Скачать подробную программу курса”", getText);

    }

    @Test
    @DisplayName("Subscription on OTUS")
    public void subscribeOtusTest() {
        driver.get("https://otus.ru");
        logger.info("Opened OTUS");
        By fieldEmail = By.name("email");
        By success = By.xpath("//p[contains(@class, \"subscribe-modal__success\")]");


        getWebElement(fieldEmail).click();
        getWebElement(fieldEmail).clear();
        getWebElement(fieldEmail).sendKeys("test.unroll07@gmail.com");
        getWebElement(fieldEmail).submit();
        logger.info("Subscribe");
        String text = getWebElement(success).getText();
        Assertions.assertEquals("Вы успешно подписались", text);


    }


    private WebElement getWebElement(By locator) {
        return new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @AfterAll
    static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


}


