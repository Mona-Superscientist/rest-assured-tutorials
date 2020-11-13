package specs;

import helpers.EmployeeHelper;
import helpers.Assert;
import io.restassured.response.Response;
import org.testng.annotations.*;

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
        Assert.okStatusCode(response);
    }

    @Test
    public void testDeleteEmployeeSuccessMessageContent() {
        Response response = employeeHelper.deleteEmployee(employeeId);
        assertEquals(response.jsonPath().get("message"), "Successfully! Record has been deleted");
    }
}
