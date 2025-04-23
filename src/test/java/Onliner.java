import org.innowise.config.URLsEnum;
import org.innowise.ui.onliner.CartPage;
import org.innowise.ui.onliner.HomePage;
import org.innowise.ui.onliner.ProductPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Onliner {

    private static WebDriver driver;
    protected static final Logger logger = LoggerFactory.getLogger(Onliner.class);
    private static final String PRODUCT_NAME = "Телефон Apple iPhone 16e 128GB (белый)";


    @BeforeAll
    public static void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(URLsEnum.ONLINER_URL.getUrl());
    }


    @Test
    @Order(1)
    public void searchProducts(){
        HomePage homePage = new HomePage(driver);
        homePage.searchProduct(PRODUCT_NAME);

        homePage.switchToSearchFrame();

        homePage.clickOnProductItem();

        homePage.switchToDefaultContent();

        ProductPage productPage = new ProductPage(driver);
        assertEquals(PRODUCT_NAME, productPage.getProductTitle(), "Titles do not match");
    }


    @Test
    @Order(2)
    public void addingProducts(){
        ProductPage productPage = new ProductPage(driver);

        assertEquals(PRODUCT_NAME, productPage.getProductTitle(), "Titles do not match");
        assertTrue(productPage.getProductSpecs().isDisplayed(), "Product specs is not displayed");

        productPage.clickFirstShopButton();
    }

    @Test
    @Order(3)
    public void addedProductIsDisplayed(){
        ProductPage productPage = new ProductPage(driver);

        productPage.clickGoToCart();

        CartPage cartPage = new CartPage(driver);

        assertTrue(cartPage.getProductTitle().isDisplayed(), "Product title is not displayed");

    }


    @Test
    @Order(4)
    public void removingProductFromCart(){
        CartPage cartPage = new CartPage(driver);

        cartPage.removeFromCart();

        assertTrue(cartPage.getRemoveFromCartConfirmation().isDisplayed(), "Product title is not displayed");
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}