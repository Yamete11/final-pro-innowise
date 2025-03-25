import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Demoqa {

    private final String baseUrl = "https://demoqa.com/";
    private String userId;
    private String token;
    private final String bookId = "9781449325862";
    private String username = "1d2tcS1estsSa12345!";
    private String password = "TestPassword123!";

    @Test
    @Order(1)
    public void registerUser() {
        String requestBody = "{"
                + "\"userName\": \"" + username + "\","
                + "\"password\": \"" + password + "\""
                + "}";

        Response response = given()
                .baseUri(baseUrl)
                .basePath("Account/v1/User")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post();

        response.then().statusCode(201);

        userId = response.jsonPath().getString("userID");
        System.out.println("User ID in registerUser: " + userId);
    }

    @Test
    @Order(2)
    public void loginUser() {
        System.out.println("User ID in loginUser: " + userId);
        String requestBody = "{"
                + "\"userName\": \"" + username + "\","
                + "\"password\": \"" + password + "\""
                + "}";

        Response response = given()
                .baseUri(baseUrl)
                .basePath("Account/v1/GenerateToken")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post();

        response.then().statusCode(200);
        token = response.jsonPath().getString("token");
        System.out.println("User TOKEN: " + token);
    }

    /*@Test
    @Order(3)*/
    public void addBookToProfile() {
        String requestBody = "{"
                + "\"userId\": \"" + userId + "\","
                + "\"collectionOfIsbns\": [{\"isbn\": \"" + bookId + "\"}]"
                + "}";

        Response response = given()
                .baseUri(baseUrl)
                .basePath("BookStore/v1/Books")
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post();

        response.then().statusCode(201);
        String message = response.jsonPath().getString("message");
        System.out.println("Book added: " + message);

        assertTrue(message.contains("Book added"), "Book was not added successfully");
    }
}
