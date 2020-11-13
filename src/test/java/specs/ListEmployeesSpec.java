package specs;

import helpers.EmployeeHelper;
import helpers.Assert;
import io.restassured.response.Response;
import model.Employee;
import org.testng.annotations.*;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

public class ListEmployeesSpec {
    private EmployeeHelper employeeHelper;

    @BeforeClass
    public void init() {
        employeeHelper = new EmployeeHelper();
    }

    @Test
    public void testGetAllEmployees() {
        Response response = employeeHelper.getAllEmployees();
        List<Employee> employeeList = (List<Employee>)response.getBody().path("data");
        Assert.okStatusCode(response);
        assertThat(employeeList, is(not(empty())));
        System.out.println(response.getBody().asString());
    }
}
