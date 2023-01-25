package apiRequests.Authentication;

import apiResourcesEndPoints.API_Resources;
import api_Actions_And_Assertion.ApiActions;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static pojos.pojoResponses.AccountsRes.SignInRes.accessToken;

public class AuthApis {
    private RequestSpecification reqSpec;

    public AuthApis(RequestSpecification reqSpec) { this.reqSpec=reqSpec;}

    public Response signIn(Object body)
    {

        return ApiActions.PostRequest(reqSpec,body,Method.POST, API_Resources.SIGN_IN.getEndPoint());
    }
    public Response signOut()
    {
        return ApiActions.GetRequest(reqSpec.header("Authorization", "Bearer "+ accessToken), Method.GET, API_Resources.SIGN_OUT.getEndPoint());
    }
}
