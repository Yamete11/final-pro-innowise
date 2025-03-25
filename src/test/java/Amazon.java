import org.innowise.amazon.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

//DONE
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Amazon {

    private static WebDriver driver;
    protected static final Logger logger = LoggerFactory.getLogger(Amazon.class);

    @BeforeAll
    public static void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.com/");
        VerificationPage verificationPage = new VerificationPage(driver);
        verificationPage.clickOnTryDifferentImageButton();
    }

    @Test
    @Order(1)
    public void successfulAuthorization(){
        HomePage homePage = new HomePage(driver);

        homePage.getHeader().hoverOverAccountAndListButton();

        SignInPage signInPage = new SignInPage(driver);

        signInPage.singIn("gleb.ivanov107@gmail.com", "Qwerty!1");

        homePage = new HomePage(driver);
        assertTrue(homePage.getHeader().getHelloLabel("Hello, Glebchik"), "You are not logged in");
    }

    @ParameterizedTest
    @CsvSource({
            "Apple iPhone 12 Mini, 64GB, Green - Unlocked (Renewed)",
    })
    @Order(2)
    public void searchProducts(String text){
        HomePage homePage = new HomePage(driver);
        homePage.getHeader().searchProducts(text);

        SearchPage searchPage = new SearchPage(driver);

        assertTrue(searchPage.checkResult(text), "Search results doesn't match");

        searchPage.clickFirstProductLink();
    }

    @Test
    @Order(3)
    public void addProductFromProductPage(){
        ProductPage productPage = new ProductPage(driver);
        productPage.clickOnAddProductButton();

        CartPage cartPage = new CartPage(driver);
        assertTrue(cartPage.getAlert().isDisplayed(), "Cart is not displayed");

        assertTrue(cartPage.checkTheNumberOfProduct(), "There are no cart products");
    }

    @Test
    @Order(4)
    public void addedProductIsDisplayed(){
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
    }
}
