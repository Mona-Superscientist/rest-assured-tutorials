import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class dataParameterizationExamples {

    @DataProvider(name = "zipCodesAndPlaces")
    public static Object[][] zipCodesAndPlaces() {
        return new Object[][] {
                { "us", "90210", "Beverly Hills" },
                { "us", "12345", "Schenectady" },
                { "ca", "B2R", "Waverley"}
        };
    }

    @Test(dataProvider = "zipCodesAndPlaces" )
    public void requestZipCodesFromCollection_checkPlaceNameInResponseBody_expectSpecifiedPlaceName(String countryCode, String zipCode, String expectedPlaceName) {

        given().
            pathParam("countryCode", countryCode).pathParam("zipCode", zipCode).
                log().all().
        when().
            get("http://zippopotam.us/{countryCode}/{zipCode}").
        then().
            assertThat().body("places[0].'place name'", equalTo(expectedPlaceName));
    }
}