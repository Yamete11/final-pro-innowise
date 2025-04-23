import org.innowise.config.URLsEnum;
import org.innowise.ui.amazon.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AmazonTest {

    /*private static WebDriver driver;
    protected static final Logger logger = LoggerFactory.getLogger(AmazonTest.class);

    @BeforeAll
    public static void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URLsEnum.AMAZON_URL.getUrl());
        VerificationPage verificationPage = new VerificationPage(driver);
        verificationPage.clickOnTryDifferentImageButton();
    }

    @ParameterizedTest
    @CsvSource({
            "gleb.ivanov107@gmail.com, Qwerty!1, Hello, Glebchik",
    })
    @Order(1)
    public void successfulAuthorization(String email, String password, String expectedText) throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.getHeader().hoverOverAccountAndListButton();
        SignInPage signInPage = new SignInPage(driver);
        signInPage.signIn(email, password);
        assertTrue(homePage.getHeader().getHelloLabel(expectedText), "You are not logged in");
    }

    @ParameterizedTest
    @CsvSource({
            "apple iphone 12 mini 64gb green - unlocked renewed",
    })
    @Order(2)
    public void searchProducts(String text) {
        HomePage homePage = new HomePage(driver);
        homePage.getHeader().searchProducts(text);
        SearchPage searchPage = new SearchPage(driver);
        assertTrue(searchPage.checkResult(text), "Search results don't match");
        searchPage.clickFirstProductLink();
    }

    @Test
    @Order(3)
    public void addProductFromProductPage() throws InterruptedException {
        ProductPage productPage = new ProductPage(driver);
        Thread.sleep(5000);
        productPage.clickOnAddProductButton();
        CartPage cartPage = new CartPage(driver);
        assertTrue(cartPage.getAlert().isDisplayed(), "Cart is not displayed");
        assertTrue(cartPage.checkTheNumberOfProduct(), "There are no cart products");
    }

    @Test
    @Order(4)
    public void addedProductIsDisplayed() {
        CartPage cartPage = new CartPage(driver);
        cartPage.getHeader().clickCart();
        ShoppingCart shoppingCart = new ShoppingCart(driver);
        assertTrue(shoppingCart.getProductTitle().isDisplayed(), "Cart is not displayed");
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }*/
}