package specs;

import helpers.EmployeeHelper;
import io.restassured.response.Response;
import model.Employee;
import org.apache.http.HttpStatus;
import org.testng.annotations.*;
import org.testng.Assert.*;

import static org.testng.Assert.assertEquals;

public class DeleteEmployeeSpec {

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
    public void testDeleteEmployee() {
        Response response = employeeHelper.deleteEmployee(employeeId);
        assertEquals(response.getStatusCode( ), HttpStatus.SC_OK, "Deleted");
    }
}
