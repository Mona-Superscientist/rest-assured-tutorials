import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.*;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class requestSpecBuilder {

    @DataProvider(name = "zipCodesAndPlaces")
    public static Object[][] zipCodesAndPlaces() {
        return new Object[][]{
                { "us", "90210" },
                { "us", "12345" },
                { "ca", "B2R" }
        };
    }

    private static RequestSpecification requestSpec;

    @BeforeClass
    public static void createRequestSpecification() {

        requestSpec = new RequestSpecBuilder().
            setBaseUri("http://api.zippopotam.us").
            build();
    }

    @Test(dataProvider = "zipCodesAndPlaces")
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills_withRequestSpec(String countryCode, String zipCode) {

        given().
           spec(requestSpec).
           pathParam("countryCode", countryCode).pathParam("zipCode", zipCode).
        when().
           get("/{countryCode}/{zipCode}").
        then().
           assertThat().statusCode(200);
    }

    private static ResponseSpecification responseSpec;

    @BeforeClass
    public static void createResponseSpecification() {

        responseSpec = new ResponseSpecBuilder().
            expectStatusCode(200).
            expectContentType(ContentType.JSON).
            build();
    }

    @Test
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills_withResponseSpec() {

        given().
           spec(requestSpec).
        when().
           get("http://zippopotam.us/us/90210").
        then().
           spec(responseSpec).
           and().
           assertThat().body("places[0].'place name'", equalTo("Beverly Hills"));
    }

    @Test
    public void requestUsZipCode90210_extractPlaceNameFromResponseBody_assertEqualToBeverlyHills() {

        String placeName =
           given().
              spec(requestSpec).
           when().
              get("http://zippopotam.us/us/90210").
           then().
               extract().path("places[0].'place name'");

        Assert.assertEquals("Beverly Hills", placeName);
    }
}
