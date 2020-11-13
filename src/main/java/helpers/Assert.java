package helpers;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import static org.testng.Assert.*;

public class Assert {

    public static void createStatusCode(Response response) {
        assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
    }

    public static void okStatusCode (Response response) {
        assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
    }

    public static void badRequestStatusCode (Response response) {
        assertEquals(response.getStatusCode(),HttpStatus.SC_BAD_REQUEST);
    }

    public static void successResponseBody (Response response, Object expectedResponse) {

    }
}
