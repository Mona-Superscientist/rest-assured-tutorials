package specs;

import helpers.EmployeeHelper;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GetEmployeeSpec {

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
        System.out.println(employeeId);
        return employeeId;
    }

    @Test
    public void testGetEmployeeInfo() {
        Response response = employeeHelper.getOneEmployee(employeeId);
        assertEquals(response.getStatusCode( ), HttpStatus.SC_OK, "Created");
        assertEquals(response.jsonPath().get("message"), "Successfully! Record has been fetched");
    }

    @Test
    public void testGetOneEmployee() {
        Response response = employeeHelper.getOneEmployee(1);
        Object createdEmployee = response.getBody().jsonPath().getJsonObject("data");
        assertEquals(response.getBody().jsonPath().getInt("data.id"), 1);
        assertEquals(response.getBody().jsonPath().get("data.employee_name"), "Tiger Nixon");
        assertEquals(response.getBody().jsonPath().getInt("data.employee_age"), 61);
        assertEquals(response.getBody().jsonPath().getInt("data.employee_salary"), 320800);
    }
}
