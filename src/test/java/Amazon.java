import org.innowise.amazon.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
            "iPhone",
    })
    public void searchProducts(String text) throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.getHeader().searchProducts(text);

        SearchPage searchPage = new SearchPage(driver);

        assertTrue(searchPage.checkResult(text), "Search results doesn't match");
    }

    //@Test
    public void addProductFromProductPage() throws InterruptedException {
        driver.get("https://www.amazon.com/Sony-WH-1000XM5-Canceling-Headphones-Hands-Free/dp/B09XS7JWHH/ref=sr_1_1_sspa?crid=24X9AV694AHGR&dib=eyJ2IjoiMSJ9.g6hnCk773OnoMpovWDr9eW2rMwb-hftXo_Y3i17329GJz8QwhBtUddE8K1hYPdd6udvGivcZDn1QIEJPzdkXeyHle46WEAMKvQUp5WR5C_jFbVDXguTmONF3mh4vk9VWSb6Ri5w5pyUx5qw45A_Nn0Qpybnrk03G7pvHhMa6G4MrXalhvgjNWMxs3M5EW-_Vy8grEy_fi7oe3BZWMj9ElZo-s_ZIpT3pWDyDXOOurBA.HSzeS7SkOfZkt6eRpRG79fmgteYj7qT0B9Ap3jzAdKY&dib_tag=se&keywords=Sony&qid=1742749858&sprefix=sony%2Caps%2C190&sr=8-1-spons&sp_csd=d2lkZ2V0TmFtZT1zcF9hdGY&th=1");
        ProductPage productPage = new ProductPage(driver);
        productPage.clickOnAddProductButton();

        CartPage cartPage = new CartPage(driver);
        assertTrue(cartPage.getAlert().isDisplayed(), "Cart is not displayed");

        assertTrue(cartPage.checkTheNumberOfProduct(), "There are no cart products");
    }

    //@Test
    public void addedProductIsDisplayed(){

    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
