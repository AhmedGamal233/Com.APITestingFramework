package apiRequests;

import apiResourcesEndPoints.API_Resources;
import api_Actions_And_Assertion.ApiActions;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static java.lang.String.valueOf;

public class ApiRequests {
    private RequestSpecification reqSpec;
   public ApiRequests(RequestSpecification reqSpec) { this.reqSpec=reqSpec; }

    public Response RestGetAllUsersRequest(int page)
    {
       return ApiActions.RequestWithQueryParameter(reqSpec,"page",page,Method.GET, valueOf(API_Resources.USERS.getResource()));
    }

    public Response createUser(Object body)
    {

        return ApiActions.PostRequest(reqSpec,body,Method.POST, valueOf(API_Resources.USERS.getResource()));
    }
    public Response updateUser(Object body,String id)
    {

        return ApiActions.PutRequest(reqSpec,body,"id",id,Method.PUT, valueOf(API_Resources.SPECIFIC_USERS.getResource()));
    }

}
