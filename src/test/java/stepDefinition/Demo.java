package stepDefinition;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pojos.pojoRequests.Create_UpdateUserRequest;
import pojos.pojoResponses.userCreation.CreatedUser;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static stepDefinition.ApiBaseTest.helper;
import static stepDefinition.ApiBaseTest.requests;
public class Demo {
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

    @When("i call login API for {string} user")
    public void iCallLoginAPIForUserUser() {
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

    @And("Store values from response")
    public void storeValuesFromResponse() {
    }

    @And("i validate token content")
    public void iValidateTokenContent() {
    }

    @And("i log out from session")
    public void iLogOutFromSession() {
    }



    @Given("i create {string}")
    public void iCreateUserUserWithJob() {
    }

}
