package stepDefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static helper.Helper.performValidations;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static stepDefinition.ApiBaseTest.helper;

public class CommonSteps {
    @Then("Status code {int} is returned")
    public void statusCodeIsReturned(int statusCode) {
        Response response = helper.getLatestResponse();
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().expectStatusCode(statusCode);
        responseSpecBuilder.build().validate(response);
    }
    @And("Verify following fields in response headers")
    public void verifyFollowingFieldsInResponseHeaders(DataTable table) {
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        List<List<String>> rows = table.asLists();
        performValidations(responseSpecBuilder,rows,false).build().validate(helper.getLatestResponse());
    }

    @And("Verify following fields")
    public void verifyFollowingFields(DataTable table) {
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        List<List<String>> rows = table.asLists();
        performValidations(responseSpecBuilder,rows,true).build().validate(helper.getLatestResponse());
    }

    @Given("i set Data table for set parameters")
    public void iSetDataTableForSetParameters(DataTable dataTable) {
        dataTable.asMap(String.class, String.class);
        //just put default values if not all parameters passed
        Map<String, String> defaultMap = new HashMap<>();
        defaultMap.put("","");
        defaultMap.entrySet().removeIf(values -> values.getValue() == null || "null".equalsIgnoreCase(values.getValue()) || values.getValue().isEmpty());
    }
    @And("check list includes {string} with Multi Parameters")
    public void checkListWithMultiParameters(String multiParameters) {
        asList(multiParameters.split(",")).forEach(Addon -> assertThat("pojo var", is(Addon)));
    }
}
