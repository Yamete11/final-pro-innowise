import org.innowise.relax.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

//DONE
public class Relax {

    private static WebDriver driver;
    protected static final Logger logger = LoggerFactory.getLogger(Relax.class);

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.relax.by/");
    }

    @Test
    public void searchValidation(){
        HomePage homePage = new HomePage(driver);

        homePage.acceptCookies();
        homePage.inputSearch("Luna");
        homePage.openSearchResult();

        PlacePage placePage = new PlacePage(driver);

        //placePage.closeAd();
        placePage.clickPlaceData();

        assertTrue(placePage.getAddress().isDisplayed(), "Address is not displayed");
        assertTrue(placePage.getPhoneNumber().isDisplayed(), "Phone number is not displayed");
        assertTrue(placePage.getWorkingHours().isDisplayed(), "Working hours is not displayed");
    }

    @Test
    public void filtersValidation() throws InterruptedException {
        HomePage homePage = new HomePage(driver);

        homePage.acceptCookies();
        homePage.openType("Еда");
        homePage.openGroup("Рестораны");

        GroupPage groupPage = new GroupPage(driver);
        groupPage.clickFilterButton();
        groupPage.openTypeOfFilter("Район");
        groupPage.openTypeOfFilterOption("Заводской");
        groupPage.chooseRadioBox("Еда навынос");
        groupPage.chooseFilter("Кухня", "Белорусская");
        groupPage.selectOptionFromMenu("Меню навынос", "Да");


        groupPage.clickShowButton();
        assertTrue(groupPage.checkResultQuantity(), "Result quantity is not displayed");
    }

    @ParameterizedTest
    @CsvSource({
            "Кино",
            "Спектакли",
            "События"
    })
    public void posterValidation(String section) {
        HomePage homePage = new HomePage(driver);

        homePage.acceptCookies();
        homePage.openType("Афиша, кино");

        AfishaPage afishaPage = new AfishaPage(driver);

        afishaPage.chooseSection(section);

        AfishaItemPage afishaItemPage = new AfishaItemPage(driver);

        assertEquals(section.toLowerCase(), afishaItemPage.getItemSection(), "Sections doesn't match");
        assertTrue(afishaItemPage.getFeedbackSection().isDisplayed(), "Feedback section is not displayed");

    }



    @BeforeEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
