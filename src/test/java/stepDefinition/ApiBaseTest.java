package stepDefinition;

import apiRequests.ApiRequests;
import com.fasterxml.jackson.databind.ObjectMapper;
import helper.Helper;
import io.cucumber.java.Before;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.extern.java.Log;
import org.junit.BeforeClass;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static io.restassured.RestAssured.given;
@Log
public class ApiBaseTest {
    protected static ApiRequests requests;
    protected static  RequestSpecification requestSpecificationBuilder;
    protected static  RequestSpecification requestSpecification;

    protected ObjectMapper objectMapper =new ObjectMapper();
    public static CookieFilter filter = new CookieFilter();

    public static Helper helper;



    @Before()
    @BeforeClass()
    public static void Before() throws FileNotFoundException {
    initiate();
    }
    public static void clearCookie() {
        filter = new CookieFilter();
        log.info("service cookies cleared");
    }

    private static void initiate() throws FileNotFoundException {
        clearCookie();
        if(requestSpecification ==null) {
            PrintStream stream = new PrintStream(new FileOutputStream("API_logs.txt"));
            //    requestSpecification= RestAssured.given().baseUri(baseUri).contentType(ContentType.JSON);
            requestSpecificationBuilder = new RequestSpecBuilder()
                    .setBaseUri("https://reqres.in/")
                    .setContentType(ContentType.JSON)
                    // .addHeader("ContentType", String.valueOf(ContentType.JSON))
                    .addFilter(RequestLoggingFilter.logRequestTo(stream))
                    .addFilter(ResponseLoggingFilter.logResponseTo(stream))
                    .build();
            requestSpecification = given().relaxedHTTPSValidation().log().all().spec(requestSpecificationBuilder);
        }
        requests=new ApiRequests(requestSpecification);
        helper =new Helper();
    }
}
