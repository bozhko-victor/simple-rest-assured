import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BookStoreTests {

    @Test
    void noLogsTest() {
        given()
                .get("https://demoqa.com/BookStore/v1/Books")
                .then()
                .body("books",hasSize(greaterThan(0)));
    }

    @Test
    void allLogsTest() {
        given()
                .log().all()
                .get("https://demoqa.com/BookStore/v1/Books")
                .then()
                .log().all()
                .body("books",hasSize(greaterThan(0)));
    }

    @Test
    void withSomeLogsTest() {
        given()
                .log().uri()
                .log().body()
                .get("https://demoqa.com/BookStore/v1/Books")
                .then()
                .log().body()
                .body("books",hasSize(greaterThan(0))); //asdsad#frew_DFS2
    }

    @Test
    void authorizeTest() {
        String  data = "{\n" +
                "  \"userName\": \"alex\"," +
                "  \"password\": \"asdsad#frew_DFS2\"" +
                "}"; // не фэн-шуй
//        Map<String, String> data = new HashMap<>();
//        data.put("userName", "alex");
//        data.put("password", "asdsad#frew_DFS2"); // не отработало, возможно баг на сайте

        given()
                .filter(new AllureRestAssured())
                .contentType("application/json")
                .accept("application/json")
                .body(data)
                .when()
                .log().body()
                .log().uri()
                .post("https://demoqa.com/Account/v1/GenerateToken")
                .then()
                .log().body()
                .body("status", is("Success"))
                .body("result", is("User authorized successfully."));
    }
}
