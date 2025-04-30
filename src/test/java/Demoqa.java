import io.restassured.response.Response;
import org.innowise.config.URLsEnum;
import org.innowise.ui.demoqa.LoginPage;
import org.innowise.ui.demoqa.ProfilePage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Demoqa {
    private static WebDriver driver;

    private static String userId;
    private static String token;
    private static String username;
    private static String password;
    private static final String BOOK_ID = "9781449331818";
    private static  String bookTitle;


    @BeforeAll
    public static void setup() {
        username = "user_" + UUID.randomUUID().toString().substring(0, 8);
        password = "TestPassword123!";

        String requestBody = String.format(
                "{\"userName\": \"%s\", \"password\": \"%s\"}", username, password);

        Response registrationResponse = given()
                .baseUri(URLsEnum.DEMOQA_URL.getUrl())
                .basePath("Account/v1/User")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post();

        registrationResponse.then().statusCode(201);
        userId = registrationResponse.jsonPath().getString("userID");

        Response loginResponse = given()
                .baseUri(URLsEnum.DEMOQA_URL.getUrl())
                .basePath("Account/v1/GenerateToken")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post();

        loginResponse.then().statusCode(200);
        token = loginResponse.jsonPath().getString("token");
    }

    @Test
    @Order(1)
    public void addBookToProfile() {
        String requestBody = String.format("{\"userId\": \"%s\", \"collectionOfIsbns\": [{\"isbn\": \"%s\"}]}", userId, BOOK_ID);

        Response addBookResponse = given()
                .baseUri(URLsEnum.DEMOQA_URL.getUrl())
                .basePath("BookStore/v1/Books")
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post();

        addBookResponse.then().statusCode(201);

        Response getUserResponse = given()
                .baseUri(URLsEnum.DEMOQA_URL.getUrl())
                .basePath("Account/v1/User/" + userId)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .get();

        getUserResponse.then().statusCode(200);

        String books = getUserResponse.jsonPath().getString("books");
        bookTitle = getUserResponse.jsonPath().getString("books[0].title");
        assertTrue(books.contains(BOOK_ID), "Книга не найдена в профиле пользователя");
    }

    @Test
    @Order(2)
    public void checkUI(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(URLsEnum.DEMOQA_URL.getUrl() + "login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        ProfilePage profilePage = new ProfilePage(driver);

        assertEquals(profilePage.getBook(), bookTitle, "Book is not displayed correctly");
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}