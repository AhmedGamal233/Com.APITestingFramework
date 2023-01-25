package apiTests;

import api_Actions_And_Assertion.ApiActions;
import api_Actions_And_Assertion.ApiAssertions;
import com.fasterxml.jackson.databind.DeserializationFeature;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import org.junit.Test;
import pojos.pojoRequests.Accounts.SignIn;
import pojos.pojoRequests.Create_UpdateUserRequest;
import pojos.pojoResponses.AccountsRes.SignInRes;
import pojos.pojoResponses.userCreation.CreatedUser;
import pojos.pojoResponses.userCreation.UpdatedUser;
import pojos.pojoResponses.usersAtSpecificPage.UsersPage;
import stepDefinition.ApiBaseTest;

import static pojos.pojoResponses.AccountsRes.SignInRes.accessToken;

public class TestClass extends ApiBaseTest {
    public  CreatedUser createdUser;
    public Create_UpdateUserRequest createUpdateUserRequest;
    public UpdatedUser updatedUser;




    @SneakyThrows
    @Test
    public void checkThatUserCreatedSuccessfully()  {
        createUpdateUserRequest =new Create_UpdateUserRequest();
        createUpdateUserRequest.setName("kaka");
        createUpdateUserRequest.setJob("KAKA land owner");
        Response response=requests.createUser(objectMapper.writeValueAsString(createUpdateUserRequest));
        //     ApiAssertions.assertStatusCodeIs_200(response);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        createdUser= objectMapper.readValue(response.prettyPrint(),CreatedUser.class);
        //OR
        // createdUser =response.as(CreatedUser.class);
        System.out.println(createdUser.getId());
        System.out.println(createdUser.getCreatedAt());

    }
    @Test
    public void checkGetAllUsersFromSpecificPage()
    {
        Response response= requests.RestGetAllUsersRequest(2);
        ApiActions.ApiBodyLogs(response);
        ApiAssertions.assertStatusCodeIs_200(response);
        UsersPage usersPage=response.as(UsersPage.class);
        usersPage.getPage();
        System.out.println( usersPage.getPage());
        usersPage.getData().get(0).getId();
        System.out.println(usersPage.getData().get(0).getId());


    }

    @Test
    public void updateUser(){
        //  Create_UpdateUserRequest createUpdateUserRequest =new Create_UpdateUserRequest();
        createUpdateUserRequest.setName("koko");
        createUpdateUserRequest.setJob("koko land owner");
        Response response=requests.updateUser(createUpdateUserRequest,createdUser.getId());
        updatedUser =response.as(UpdatedUser.class);
    }

    @SneakyThrows
    @Test
    public void signIn_signOut(){
        signIn =new SignIn();
        signInRes =new SignInRes();
        signIn.setUsername("msa@mimonote.com");
        signIn.setPassword("123456@a");
        signIn.set_comment("username and password are mandatory");
        signIn.setDeviceServiceId("string");
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Response response=authApis.signIn(objectMapper.writeValueAsString(signIn));
        helper.setLatestResponse(response);
        signInRes= objectMapper.readValue(helper.getLatestResponse().prettyPrint(), SignInRes.class);
        accessToken =signInRes.getToken();
        authApis.signOut();
    }
}
