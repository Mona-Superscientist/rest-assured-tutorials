package specs;

import helpers.EmployeeHelper;
import helpers.Assert;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.*;
import static org.testng.Assert.*;


public class CreateEmployeeSpec {
    private EmployeeHelper employeeHelper;

    @BeforeClass
    public void init() {
        employeeHelper = new EmployeeHelper();
    }


    @Test
    public void testPOSTCreatePersonReturnsStatus200OK() {
        Response response = employeeHelper.createEmployee("Ahmed", "30", "3000");
        System.out.println(response.getBody().asString());
        Assert.okStatusCode(response);
    }

    @Test
    public void testEmployeeIsCreatedSuccessfully() {
        Response response = employeeHelper.createEmployee("Rafik", "28", "100");
        Object createdEmployee = response.getBody().jsonPath().getJsonObject("data");
        Integer id = response.getBody().jsonPath().get("data.id");
        assertEquals(response.getBody().jsonPath().get("data.name"), "Rafik");
        assertEquals(response.getBody().jsonPath().get("data.age"), "28");
        assertEquals(response.getBody().jsonPath().get("data.salary"), "100");
    }

    @Test
    public void testInvalidAge() {
        Response response = employeeHelper.createEmployee("Rafik", "-280", "100");
        Object createdEmployee = response.getBody().jsonPath().toString();
        System.out.println(createdEmployee);
    }
}
