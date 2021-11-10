import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.core.Is.is;

public class ReqresinTests {


    //        request: https://reqres.in/api/login
//        data: {
//            "email": "eve.holt@reqres.in",
//                    "password": "cityslicka"
//        }
//        response: {
//            "token": "QpwL5tke4Pnpja7X4"
//        }
//        status: 200
    @Test
    void successfulLogin() {
        given()
                .contentType(JSON)
                .body("{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}")
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));

    }

    @Test
    void negativeLogin() {
//        System.out.println( //используем закомиченные строки для получения названия и значения ошибки из логов
        given()
                .contentType(JSON)
                .body("{\"email\": \"eve.holt@reqres.in\"}")
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(400)
//                .extract().asString());
                .body("error", is("Missing password")); //missing - потерянный

    }
}
