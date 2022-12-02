package apiTests;

import api_Actions_And_Assertion.ApiActions;
import api_Actions_And_Assertion.ApiAssertions;
import com.fasterxml.jackson.databind.DeserializationFeature;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import org.junit.Test;
import pojos.pojoRequests.Create_UpdateUserRequest;
import pojos.pojoResponses.userCreation.CreatedUser;
import pojos.pojoResponses.userCreation.UpdatedUser;
import pojos.pojoResponses.usersAtSpecificPage.UsersPage;
import stepDefinition.ApiBaseTest;

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
}
