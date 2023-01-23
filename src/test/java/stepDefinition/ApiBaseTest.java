package stepDefinition;

import apiRequests.ApiRequests;
import com.fasterxml.jackson.databind.ObjectMapper;
import helper.Helper;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.BeforeClass;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static io.restassured.RestAssured.given;
@Slf4j
public class ApiBaseTest {
    protected static ApiRequests requests;
    protected static  RequestSpecification requestSpecificationBuilder;
    protected static  RequestSpecification requestSpecification;

    protected static ObjectMapper objectMapper =new ObjectMapper();
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


    // this property is injected by VSTS when running API tests
    private static final String BASE_PATH_PROPERTY = "base.path";

    // this property should be set if running locally + not setting base.path explicitly
    //run by -Ddigitalx.env=env1 -Dcucumber.filter.tags="(not @ignore)"
    private static final String DAL_ENV_PROPERTY = "digitalx.env";


    public void setBaseUriByEnvironment() {

        String basePath = System.getProperty(BASE_PATH_PROPERTY);
        log.info("{} = {}" , BASE_PATH_PROPERTY , basePath);

        String dalEnv = System.getProperty(DAL_ENV_PROPERTY);
        log.info("{} = {}" , DAL_ENV_PROPERTY , dalEnv);

        // VSTS sets -Dbase.path=$(dxlServiceUrl)/  (see Pipelines/Task Groups/Maven BDD)
        if (StringUtils.isNotEmpty(basePath)) {
            setBaseUri(basePath);
        }
        // when running via local maven/IDE you can either set base.path or dal.env
        else if (StringUtils.isNotEmpty(dalEnv)) {
            if ("local".equalsIgnoreCase(dalEnv)) {
                setBaseUri("http://localhost:3333/");
            } else if ("env1".equalsIgnoreCase(dalEnv)) {
                setBaseUri("");
            } else if ("env2".equalsIgnoreCase(dalEnv)) {
                setBaseUri("");
            } else if ("env3".equalsIgnoreCase(dalEnv)) {
                setBaseUri("");
            } else {
                log.error("unknown {} '{}'" , DAL_ENV_PROPERTY , dalEnv);
            }
        } else {
            log.error("please specify either '{}' or (for local testing) '{}'" , BASE_PATH_PROPERTY , DAL_ENV_PROPERTY);
        }
    }
    private void setBaseUri(String host) {
        RestAssured.baseURI = host;
        log.info("RestAssured.baseURI = {}" , RestAssured.baseURI);
    }
}
