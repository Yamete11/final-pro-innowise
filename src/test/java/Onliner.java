import org.innowise.onliner.pages.CartPage;
import org.innowise.onliner.pages.HomePage;
import org.innowise.onliner.pages.ProductPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//DONE
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Onliner {

    private static WebDriver driver;
    protected static final Logger logger = LoggerFactory.getLogger(Onliner.class);
    String product = "Телефон Apple iPhone 16e 128GB (белый)";


    @BeforeAll
    public static void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.onliner.by/");
    }


    @Test
    @Order(1)
    public void searchProducts() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.searchProduct(product);

        homePage.switchToSearchFrame();

        homePage.clickOnProductItem();

        homePage.switchToDefaultContent();

        ProductPage productPage = new ProductPage(driver);
        assertEquals(product, productPage.getProductTitle(), "Titles do not match");
    }


    @Test
    @Order(2)
    public void addingProducts() throws InterruptedException {
        ProductPage productPage = new ProductPage(driver);

        assertEquals(product, productPage.getProductTitle(), "Titles do not match");
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
    public void removingProductFromCart() throws InterruptedException {
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
