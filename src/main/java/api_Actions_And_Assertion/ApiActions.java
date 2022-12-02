package api_Actions_And_Assertion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.binary.Base64;
import pojos.pojoRequests.DecodedToken;

import java.io.IOException;
import java.util.Collection;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiActions {
    public static CookieFilter filter =new CookieFilter();
    private static Method SpecifyHTTPMethod(Method method)
    {
        switch(method) {
            case GET:
                method =Method.GET;
                break;
            case POST:
                method =Method.POST;
                break;
            case PUT:
                method =Method.PUT;
                break;
            case PATCH:
                method =Method.PATCH;
                break;
            case DELETE:
                method =Method.DELETE;
                break;
            default:
                System.out.println("please check HTTP selected method");
        }
        return method;
    }
    public static Response PostRequest(RequestSpecification requestSpecification,Object body,Method method,String endPoint){
         return requestSpecification.body(body).request(SpecifyHTTPMethod(method),endPoint).then().log().all().extract().response();

    }

    public static Response PutRequest(RequestSpecification requestSpecification,Object body, String paramKey , String paramValue, Method method,String endPoint){
        return requestSpecification.body(body).pathParam(paramKey,paramValue).request(SpecifyHTTPMethod(method),endPoint).then().log().all().extract().response();
    }

    public static Response DeleteRequest(RequestSpecification requestSpecification ,String paramKey , String paramValue, Method method,String endPoint){
        return requestSpecification.pathParam(paramKey,paramValue).request(SpecifyHTTPMethod(method),endPoint).then().log().all().extract().response();
    }
    public static Response getRequestWithRedirection(RequestSpecification requestSpecification ,String paramKey , String paramValue, Method method,String endPoint) {
      return requestSpecification.redirects().follow(false).filter(filter).pathParam(paramKey,paramValue).request(SpecifyHTTPMethod(method),endPoint).then().log().all().extract().response();
    }
    public static Response RequestWithQueryParameter(RequestSpecification requestSpecification, String paramKey , int paramValue, Method method , String endPoint)
    {
        return requestSpecification.queryParam(paramKey, paramValue).request(SpecifyHTTPMethod(method), endPoint);
    }
    public static Response RequestWithQueryParameter(RequestSpecification requestSpecification, String paramKey , String paramValue, Method method , String endPoint)
    {
       return requestSpecification.queryParam(paramKey, paramValue).request(SpecifyHTTPMethod(method), endPoint);
    }
    public static Response RequestWithMultiQueryParameter(RequestSpecification requestSpecification, String paramKey , Collection pramValues, Method method , String endPoint)
    {
        return requestSpecification.queryParam(paramKey,pramValues).request(SpecifyHTTPMethod(method),endPoint);
    }
    public static Response RequestWithPathParameter(RequestSpecification requestSpecification, String paramKey , int paramValue, Method method , String endPoint)
    {
        return requestSpecification.pathParam(paramKey,paramValue).request(SpecifyHTTPMethod(method),endPoint);
    }
    public static Response RequestWithPathParameter(RequestSpecification requestSpecification, String paramKey , String paramValue, Method method , String endPoint)
    {
        return requestSpecification.pathParam(paramKey,paramValue).request(SpecifyHTTPMethod(method),endPoint);
    }
    public static Response RequestWithMultiPathParameter(RequestSpecification requestSpecification, String paramKey , Collection paramValues, Method method , String endPoint)
    {
        return requestSpecification.pathParam(paramKey,paramValues).request(SpecifyHTTPMethod(method),endPoint);
    }

    public static void compareResponseWithJsonSchema(Response response ,String jsonSchemaFile)
    {
        response.then().body(matchesJsonSchemaInClasspath(jsonSchemaFile));
    }

    public static void compareRequestWithJsonSchema(RequestSpecification requestSpecification ,String jsonSchemaFile)
    {
        requestSpecification.body(matchesJsonSchemaInClasspath(jsonSchemaFile));
    }

    public static String extractDataFromResponse(Response response ,String jsonData)
    {
        response.then().extract().response();
        JsonPath path= response.jsonPath();
        return path.get(jsonData);
    }

    //logs with reqSpec
    public static void ApiLogs(RequestSpecification requestSpecification, String paramKey , Collection paramValues, Method method , String endPoint)
    {
        requestSpecification.pathParam(paramKey,paramValues).request(SpecifyHTTPMethod(method),endPoint).then().log().body();
    }
    //logs with response
    public static void ApiBodyLogs(Response response)
    {
        response.then().log().body();
    }
    public static void ApiLogsIfValidationFails(Response response)
    {
        response.then().log().ifValidationFails();
    }
    public static void ApiHeadersLogs(Response response)
    {
        response.then().log().headers();
    }
    public static void ApiIfErrorLogs(Response response)
    {
        response.then().log().ifError();
    }

    public String getJsonPath(Response response,String key){

        JsonPath js = new JsonPath(response.asString());

        return js.get(key).toString();
    }
    public String getJsonPath(Object body) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
       return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(body);
    }
    public void decodeToken(Response response) throws IOException {
        String[] dotSplitedJWT = response.jsonPath().get("id_token").toString().split("\\.");
        String jwtBody = dotSplitedJWT[1];
        String decodedBody = new String((new Base64(true)).decode(jwtBody),Charsets.toCharset("UTF-8"));
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.readValue(decodedBody, DecodedToken.class);
    }

    public Response postRequest(String parameter) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("this.idmPath").setBasePath("connect/token").log(LogDetail.METHOD).log(LogDetail.URI).addFormParam("", "");
        return given().redirects().follow(false).filter(filter).relaxedHTTPSValidation().log().all().spec(requestSpecBuilder.build()).post().then().extract().response();
    }

    public Response putRequest(String body) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("").setBasePath("")
                .setContentType(ContentType.JSON)
                .setBody(body);
        return given().redirects().follow(false).filter(filter).relaxedHTTPSValidation().log().all().spec(requestSpecBuilder.build()).put().then().log().all().extract().response();
    }
    public Response postRequest2(String body) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("")
                .setBasePath("")
                .setContentType(ContentType.JSON)
                .setBody(body);
        return given().redirects().follow(false).filter(filter).relaxedHTTPSValidation().log().all().spec(requestSpecBuilder.build()).post().then().log().all().extract().response();
    }
    public Response deleteRequest(String Parameter) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("this.idmPath").setBasePath("internal-api/accounts/{accountId}/subscriptions").addPathParam("", "").addQueryParam("", "");
        return given().redirects().follow(false).filter(filter).relaxedHTTPSValidation().log().all().spec(requestSpecBuilder.build()).delete().then().log().all().extract().response();
    }

}
