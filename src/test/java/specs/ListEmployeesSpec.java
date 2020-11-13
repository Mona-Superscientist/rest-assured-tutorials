package specs;

import helpers.EmployeeHelper;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;

public class ListEmployeesSpec {
    private EmployeeHelper employeeHelper;

    @BeforeClass
    public void init() {
        employeeHelper = new EmployeeHelper();
    }

    @Test
    public void testGetAllEmployees() {
        Response response = employeeHelper.getAllEmployees();
        assertEquals(response.getStatusCode( ), HttpStatus.SC_OK, "Created");
        System.out.println(response.getBody().asString());
    }
}
