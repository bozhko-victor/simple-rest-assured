import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.core.Is.is;

public class ReqresinTests {

    @Test
    void checkNameSingleUser() {
        get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("data.first_name", is("Janet"));

    }

    @Test
    void checkSizeListRessourse() {
        get("https://reqres.in/api/unknown")
                        .then()
                        .statusCode(200)
                        .body("per_page", is(6));

    }

    @Test
    void checkListRessourse() {
        Response  response =
        get("https://reqres.in/api/unknown")
                .then()
                .extract().response();

        assertThat(response.path("data").toString())
                .contains("fuchsia rose");

    }

    @Test
    void checkListRessourseWithIndex() {
        get("https://reqres.in/api/unknown")
                .then()
                .statusCode(200)
                .body("data[2].name", is("true red"));  //"путь для бедных" - до создания объекта

    }

    @Test
    void checkCreatedAt() {
        given()
                .contentType(JSON)
                .body("{ \"name\": \"morpheus\", \"job\": \"leader\" }")
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("job", is("leader"));
    }
}
