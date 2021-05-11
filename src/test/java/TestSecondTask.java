import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.hc.core5.util.Asserts;
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
    @Disabled("true")
    @DisplayName("Check address on Otus")
    public void checkoutAddressTest() {
        driver.get("https://otus.ru ");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        By button = By.xpath("//*[@title=\"Контакты\"]");
        By address = By.xpath("//div[3][contains(@class, \"styles__Block-c0qfa0-1 dckUnw\")]");


        driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
        getWebElement(button).click();
        driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);

        String getAddress = getWebElement(address).getText();
        System.out.println(getAddress);
        Assertions.assertEquals(getAddress, "125167, г. Москва, Нарышкинская аллея., д. 5, стр. 2");


    }

    @Test
    @DisplayName("Check title on tele2")
    public void checkTitleTele2() {

        driver.get("https://msk.tele2.ru/shop/number");
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        String title = driver.getTitle();
        Assertions.assertEquals(title, "Красивые номера - купить красивый федеральный номер телефона Tele2 Москва" +
                " и Московская область, продажа красивых мобильных номеров");


    }

    @Test
    @DisplayName("Check phone numbers")
    public void tele2Test() {
        driver.get("https://msk.tele2.ru/shop/number");
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

        By search = By.id("searchNumber");
        By phoneBlock = By.xpath("//div[contains(@class, \"new-number-block\")]");
        By table = By.xpath("//table");
        getWebElement(search).click();
        getWebElement(search).clear();
        getWebElement(search).sendKeys("97");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement element = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(phoneBlock));


    }

    @Test
    @DisplayName("Assert test on FAQ")
    public void checkFAQTest() {
        driver.get("https://otus.ru");
        By buttonFAQ = By.xpath("//a[@title= \"FAQ\"]");
        By buttonQuestion = By.xpath("/html/body/div[1]/div/div[3]/div[2]/div[2]/div[4]/div[1]");
        By buttonAnswer = By.xpath("/html/body/div[1]/div/div[3]/div[2]/div[2]/div[4]/div[2]");


        getWebElement(buttonFAQ).click();
        getWebElement(buttonQuestion).click();
        String getText = getWebElement(buttonAnswer).getText();
        Assertions.assertEquals(getText, "Программу курса в сжатом виде можно увидеть на странице курса после блока с преподавателями." +
                " Подробную программу курса можно скачать кликнув на “Скачать подробную программу курса”");

    }

    @Test
    @DisplayName("Subscription on OTUS")
    public void subscribeOtusTest() {
        driver.get("https://otus.ru");
        By fieldEmail = By.name("email");
        By submitButton = By.xpath("//div[contains(@class, \"footer2__subscribe-button button button_blue button_as-input\")]");
        By success = By.xpath("//p[contains(@class, \"subscribe-modal__success\")]");


        getWebElement(fieldEmail).click();
        getWebElement(fieldEmail).clear();
        getWebElement(fieldEmail).sendKeys("test.unroll07@gmail.com");
        getWebElement(fieldEmail).submit();
        String text = getWebElement(success).getText();
        Assertions.assertEquals(text, "Вы успешно подписались");


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


