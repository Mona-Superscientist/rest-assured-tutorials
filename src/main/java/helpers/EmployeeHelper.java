package helpers;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.Employee;
import constants.Endpoints;
import org.apache.http.HttpStatus;
import config.ConfigManager;

public class EmployeeHelper {
    private static final String BASE_URL = ConfigManager.getInstance().getString("baseURL");

    public EmployeeHelper () {
        RestAssured.baseURI = BASE_URL;
        RestAssured.useRelaxedHTTPSValidation();
    }

    public Response getAllEmployees() {

        Response response = RestAssured
                .given( ).log( ).all( )
                .contentType(ContentType.JSON)
                .get(Endpoints.GET_ALL_EMPLOYEES)
                .andReturn( );

        return response;
    }


    public Response createEmployee(String name, String age, String salary) {

        Employee employee = new Employee( );
        employee.setName(name);
        employee.setAge(age);
        employee.setSalary(salary);

        Response response = RestAssured.given( )
                .contentType(ContentType.JSON)
                .when( )
                .body(employee)
                .post(Endpoints.CREATE_EMPLOYEE)
                .andReturn( );

        return response;
    }

    public Response updateEmployee(Integer id, String name, String age, String salary){

        Employee employee = new Employee( );
        employee.setName(name);
        employee.setAge(age);
        employee.setSalary(salary);

        Response response = RestAssured.given().contentType(ContentType.JSON)
                .pathParam("id",id)
                .when().body(employee)
                .put(Endpoints.UPDATE_EMPLOYEE)
                .andReturn();

        return response;


    }


    public Response deleteEmployee(Integer id){

        Response response = RestAssured.given().contentType(ContentType.JSON)
                .pathParam("id",id)
                .log().all()
                .when()
                    .delete(Endpoints.DELETE_EMPLOYEE)
                    .andReturn();

        return response;
    }
}
