package specs;

import helpers.EmployeeHelper;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class UpdateEmployeeSpec {
    private Integer employeeId;
    private EmployeeHelper employeeHelper;

    @BeforeClass
    public void init() {
        employeeHelper = new EmployeeHelper();
    }

    @BeforeMethod
    public Integer createEmployee() {
        Response response = employeeHelper.createEmployee("Ahmed", "20", "5000");
        employeeId = response.getBody().jsonPath().get("data.id");
        return employeeId;
    }

    @Test
    public void testUpdateEmployee() {
        Response response = employeeHelper.updateEmployee(employeeId, "mona", "31", "10");
        assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        System.out.println(response.getBody().asString());
        assertEquals(response.getBody().jsonPath().get("data.name"), "mona");
        assertEquals(response.getBody().jsonPath().get("data.age"), "31");
        assertEquals(response.getBody().jsonPath().get("data.salary"), "10");
        assertEquals(response.getBody().jsonPath().get("message"), "Successfully! Record has been updated.");
    }
}
