package apiRequests.userContent.userNoteCategories;

import apiResourcesEndPoints.API_Resources;
import api_Actions_And_Assertion.ApiActions;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static pojos.pojoResponses.AccountsRes.SignInRes.accessToken;

public class UserNoteCategoriesRequests {
    private RequestSpecification reqSpec;

    public UserNoteCategoriesRequests(RequestSpecification reqSpec) {
        this.reqSpec = reqSpec;
    }

    private RequestSpecification buildNoteCategory(int noteCategoryId, int defaultNoteCategoryId) {
        return reqSpec.header("Authorization", "Bearer " + accessToken).pathParams("noteCategoryId",
                noteCategoryId, "defaultNoteCategoryId", defaultNoteCategoryId);
    }

    private RequestSpecification buildNoteCategories(Map<String, Object> parameters) {
        return reqSpec.header("Authorization", "Bearer " + accessToken).queryParams(parameters);
    }

    public Response getNoteCategories(Map<String, Object> parameters) {

        return ApiActions.GetRequest(buildNoteCategories(parameters), Method.GET,
                API_Resources.NOTE_CATEGORIES.getEndPoint());
    }

    public Response getNoteCategory(int noteCategoryId, int defaultNoteCategoryId) {
        return ApiActions.GetRequest(buildNoteCategory(noteCategoryId, defaultNoteCategoryId), Method.GET,
                API_Resources.NOTE_CATEGORIES.getEndPoint() + "/{noteCategoryId}/{defaultNoteCategoryId}");
    }

    public Response editNoteCategory(Object body) {

        return ApiActions.PutRequest(reqSpec.header("Authorization", "Bearer " + accessToken), body, Method.PUT,
                API_Resources.NOTE_CATEGORIES.getEndPoint());
    }

    public Response createNoteCategory(Object body) {

        return ApiActions.PostRequest(reqSpec.header("Authorization", "Bearer " + accessToken), body,
                Method.POST, API_Resources.NOTE_CATEGORIES.getEndPoint());
    }

    public Response deleteNoteCategory(int noteCategoryId, int defaultNoteCategoryId) {

        return ApiActions.DeleteRequest(buildNoteCategory(noteCategoryId, defaultNoteCategoryId), Method.DELETE,
                API_Resources.NOTE_CATEGORIES.getEndPoint() + "/{noteCategoryId}/{defaultNoteCategoryId}");
    }
}
