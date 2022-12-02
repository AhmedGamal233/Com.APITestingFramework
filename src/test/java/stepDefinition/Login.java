package stepDefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import pojos.pojoRequests.Create_UpdateUserRequest;
import pojos.pojoResponses.userCreation.CreatedUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static helper.Helper.performValidations;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static stepDefinition.ApiBaseTest.helper;
import static stepDefinition.ApiBaseTest.requests;
public class Login{
    public  CreatedUser createdUser;
    public Create_UpdateUserRequest createUpdateUserRequest;
    @Given("i create {string} user with job {string}")
    public void iCreateUserUserWithJob(String user, String job) {
        createUpdateUserRequest =new Create_UpdateUserRequest();
        createUpdateUserRequest.setName(user);
        createUpdateUserRequest.setJob(job);
        Response response=requests.createUser(createUpdateUserRequest);
        helper.setLatestResponse(response);
//        //     ApiAssertions.assertStatusCodeIs_200(response);
        createdUser =helper.getLatestResponse().as(CreatedUser.class);
        System.out.println(createdUser.getId());
        System.out.println(createdUser.getCreatedAt());
    }

    @When("login settings are requested")
    public void loginSettingsAreRequested() {
     helper.getLatestResponse().prettyPrint();
    }

    @Then("Status code {int} is returned")
    public void statusCodeIsReturned(int arg0) {

    }

    @When("i call login API for {string} user")
    public void iCallLoginAPIForUserUser() {
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

    @And("i save cookie content")
    public void iSaveCookieContent() {
    }

    @ParameterType("parameterType|anotherOne|anotherOne|") //|without anyAfter is for empty parameter
    public String kbaCondition(String entry) {
        return entry;
    }


    @When("user {word} {kbaCondition}")
    public void userParameterType() {
    }

    @When("i get valid user code")
    public void iGetValidUserCode() {
    }

    @Then("i generate user tokens")
    public void iGenerateUserTokens() {
    }

    @And("Store values from response")
    public void storeValuesFromResponse() {
    }

    @And("i validate token content")
    public void iValidateTokenContent() {
    }

    @And("i log out from session")
    public void iLogOutFromSession() {
    }

    @And("Delete list includes {string} passed in the request and not related to the plan")
    public void deleteListIncludesPassedInTheRequestAndNotRelatedToThePlan(String multiParameters) {
        asList(multiParameters.split(",")).forEach(Addon -> assertThat("", is(Addon)));
    }

    @Given("i set Data table for set parameters")
    public void iSetDataTableForSetParameters(DataTable dataTable) {
        dataTable.asMap(String.class, String.class);
        //just put default values if not all parameters passed
        Map<String, String> defaultMap = new HashMap<>();
        defaultMap.put("","");
        defaultMap.entrySet().removeIf(values -> values.getValue() == null || "null".equalsIgnoreCase(values.getValue()) || values.getValue().isEmpty());
    }

    @Given("i create {string}")
    public void iCreateUserUserWithJob() {
    }

}
