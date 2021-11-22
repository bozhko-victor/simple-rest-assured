package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

public class DemoWebShopTests {

    @Test
    void addToCartTest() {
        Response response =
        given()
                .log().all()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("product_attribute_72_5_18=53&product_attribute_72_6_19=54&product_attribute_72_3_20=57&" +
                        "product_attribute_72_8_30=93&product_attribute_72_8_30=94&" +
                        "product_attribute_72_8_30=95&addtocart_72.EnteredQuantity=1")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                .then()
                .statusCode(200)
                .body("success", is (true))
                .body("message", is ("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                .body("updatetopcartsectionhtml", is ("(1)"))
                .extract().response();

        System.out.println(response.asString());
        System.out.println(response.path("updatetopcartsectionhtml").toString());
    }

    @Test
    void addToCartWithCookieTest() {
        Response response =
                given()
                        .log().all()
                        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                        .body("product_attribute_72_5_18=53&product_attribute_72_6_19=54&product_attribute_72_3_20=57&" +
                                "product_attribute_72_8_30=93&product_attribute_72_8_30=94&" +
                                "product_attribute_72_8_30=95&addtocart_72.EnteredQuantity=1")
                        .cookie("Nop.customer=ab8f9e6d-ce9e-41d8-8828-94f5a58b535e;")
                        .when()
                        .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                        .then()
                        .statusCode(200)
                        .body("success", is (true))
                        .body("message", is ("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
//                        .body("updatetopcartsectionhtml", is ("(1)")) // todo refactor, check increase + 1
                        .extract().response();

//        System.out.println(response.asString());
        System.out.println(response.path("updatetopcartsectionhtml").toString());
    }

}
