import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.get;

public class CombinedTests {


    @Test
    @DisplayName("Combined test (API + UI)")
    void checkSearchResult() {

        get("http://demowebshop.tricentis.com/search?q=Jewelry")
                .then()
                .statusCode(200);

        open("http://demowebshop.tricentis.com/search?q=Jewelry");
        $(".search-results").shouldHave(text("Create Your Own Jewelry"));

    }


}


