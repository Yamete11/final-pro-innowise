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

public class Onliner {

    private static WebDriver driver;
    protected static final Logger logger = LoggerFactory.getLogger(Onliner.class);

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.onliner.by/");
    }


    @Test
    public void searchProducts() throws InterruptedException {
        String product = "Телефон Samsung Galaxy A52 SM-A525F/DS 4GB/128GB (черный)";

        HomePage homePage = new HomePage(driver);
        homePage.searchProduct(product);
        homePage.switchToSearchFrame();
        homePage.clickOnProductItem();
        homePage.switchToDefaultContent();

        ProductPage productPage = new ProductPage(driver);
        assertEquals(product, productPage.getProductTitle(), "Titles do not match");
    }


    @Test
    public void addingProducts() throws InterruptedException {
        HomePage homePage = new HomePage(driver);

        homePage.acceptCookies();

        String text = homePage.openFirstProduct();

        ProductPage productPage = new ProductPage(driver);

        assertEquals(text, productPage.getProductTitle(), "Titles do not match");
        assertTrue(productPage.getProductSpecs().isDisplayed(), "Product specs is not displayed");

        productPage.clickFirstShopButton();

        productPage.clickGoToCart();

        CartPage cartPage = new CartPage(driver);

        assertTrue(cartPage.getProductTitle().isDisplayed(), "Product title is not displayed");

    }

    @Test
    public void removingProductFromCart() throws InterruptedException {
        HomePage homePage = new HomePage(driver);

        homePage.acceptCookies();

        String text = homePage.openFirstProduct();

        ProductPage productPage = new ProductPage(driver);

        productPage.clickFirstShopButton();

        productPage.clickGoToCart();

        CartPage cartPage = new CartPage(driver);

        cartPage.removeFromCart();

        assertTrue(cartPage.getRemoveFromCartConfirmation().isDisplayed(), "Product title is not displayed");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
