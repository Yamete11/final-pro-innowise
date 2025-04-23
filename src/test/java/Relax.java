import org.innowise.config.URLsEnum;
import org.innowise.ui.relax.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

public class Relax {

    private static WebDriver driver;
    protected static final Logger logger = LoggerFactory.getLogger(Relax.class);

    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(URLsEnum.RELAX_URL.getUrl());
    }

    @ParameterizedTest
    @CsvSource({
            "Luna"
    })
    @Order(1)
    public void searchValidation(String inputText){
        HomePage homePage = new HomePage(driver);

        homePage.acceptCookies();
        homePage.inputSearch(inputText);
        homePage.openSearchResult();

        PlacePage placePage = new PlacePage(driver);

        placePage.closeAd();
        placePage.clickPlaceData();

        assertTrue(placePage.getAddress().isDisplayed(), "Address is not displayed");
        assertTrue(placePage.getPhoneNumber().isDisplayed(), "Phone number is not displayed");
        assertTrue(placePage.getWorkingHours().isDisplayed(), "Working hours is not displayed");
    }

    @ParameterizedTest
    @CsvSource({
            "Еда, Рестораны, Район, Заводской, Еда навынос, Кухня, Белорусская, Меню навынос, Да"
    })
    @Order(2)
    public void filtersValidation(String type, String group, String typeOfFilter, String typeOfFilterOption, String radioBox, String filterGroup, String filterOption, String menuTitle, String menuOption){
        HomePage homePage = new HomePage(driver);

        homePage.acceptCookies();
        homePage.openType(type);
        homePage.openGroup(group);

        GroupPage groupPage = new GroupPage(driver);
        groupPage.clickFilterButton();
        groupPage.openTypeOfFilter(typeOfFilter);
        groupPage.openTypeOfFilterOption(typeOfFilterOption);
        groupPage.chooseRadioBox(radioBox);
        groupPage.chooseFilter(filterGroup, filterOption);
        groupPage.selectOptionFromMenu(menuTitle, menuOption);


        groupPage.clickShowButton();
        assertTrue(groupPage.checkResultQuantity(), "Result quantity is not displayed");
    }

    @ParameterizedTest
    @CsvSource({
            "Кино, 'Афиша, кино'",
            "Спектакли, 'Афиша, кино'",
            "События, 'Афиша, кино'"
    })
    @Order(3)
    public void posterValidation(String section, String type) {
        HomePage homePage = new HomePage(driver);

        homePage.acceptCookies();
        homePage.openType(type);

        AfishaPage afishaPage = new AfishaPage(driver);

        afishaPage.chooseSection(section);

        AfishaItemPage afishaItemPage = new AfishaItemPage(driver);

        assertEquals(section.toLowerCase(), afishaItemPage.getItemSection(), "Sections doesn't match");
        assertTrue(afishaItemPage.getFeedbackSection().isDisplayed(), "Feedback section is not displayed");
    }


    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
