import org.innowise.translate.HomePage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Translate {

    private static WebDriver driver;
    protected static final Logger logger = LoggerFactory.getLogger(Translate.class);
    private HomePage homePage;


    @BeforeEach
    public void setupHomePage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://translate.google.com/");
        homePage = new HomePage(driver);
        homePage.clickAcceptAll();
    }


    @ParameterizedTest
    @CsvSource({
            "English, Russian, Test, Тест",
            "French, Spanish, Bonjour, Buen día",
            "German, Italian, Guten Tag, Buona giornata"
    })
    public void uiValidation(String firstLanguage, String secondLanguage, String text, String resultText) throws InterruptedException {

        homePage.clickExpandLeftButton();
        homePage.switchLeftLanguage(firstLanguage);

        homePage.clickExpandRightButton();
        homePage.switchRightLanguage(secondLanguage);

        homePage.enterText(text);

        assertEquals(resultText, homePage.getTextResult(), "Text doesn't match");
    }

    @AfterEach
    public void tearDownHomePage() {
        if (driver != null) {
            driver.quit();
        }
    }

}
