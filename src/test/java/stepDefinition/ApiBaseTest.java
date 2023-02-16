package stepDefinition;

import apiRequests.ApiRequests;
import apiRequests.Authentication.AuthApis;
import apiRequests.userContent.userNoteCategories.UserNoteCategoriesRequests;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import helper.Helper;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.BeforeClass;
import pojos.pojoRequests.Accounts.SignIn;
import pojos.pojoResponses.AccountsRes.SignInRes;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

import static io.restassured.RestAssured.given;
import static pojos.pojoResponses.AccountsRes.SignInRes.accessToken;

@Slf4j
public class ApiBaseTest {
    protected static ApiRequests requests;
    protected static AuthApis authApis;
    protected static UserNoteCategoriesRequests userNoteCategoriesRequests;
    protected static  RequestSpecification requestSpecificationBuilder;
    protected static  RequestSpecification requestSpecification;
    protected static SignIn signIn;
    protected static SignInRes signInRes;

    public static ObjectMapper objectMapper =new ObjectMapper();
    public static CookieFilter filter = new CookieFilter();

    public static Helper helper;

    public static int hitResult;
    public static boolean expired;



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
                    .setBaseUri("https://app-api-mimo-reloaded-gateway-dev.azurewebsites.net/")
                    .setContentType(ContentType.JSON)
                    // .addHeader("ContentType", String.valueOf(ContentType.JSON))
                    .addFilter(RequestLoggingFilter.logRequestTo(stream))
                    .addFilter(ResponseLoggingFilter.logResponseTo(stream))
                    .build();
            requestSpecification = given().relaxedHTTPSValidation().log().all().spec(requestSpecificationBuilder);
        }
        requests=new ApiRequests(requestSpecification);
        authApis = new AuthApis(requestSpecification);
        userNoteCategoriesRequests = new UserNoteCategoriesRequests(requestSpecification);
        helper = new Helper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        // objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
    @SneakyThrows
    public static int generateToken(String username,String password,String DeviceServiceId) {
        signIn=new SignIn();
        signIn.setUsername(username);
        signIn.setPassword(password);
        signIn.setDeviceServiceId(DeviceServiceId);
       // objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Response response=authApis.signIn(objectMapper.writeValueAsString(signIn));
        helper.setLatestResponse(response);
        signInRes= objectMapper.readValue(helper.getLatestResponse().prettyPrint(), SignInRes.class);
        accessToken =signInRes.getToken();
        setToken();
       // String expireDate =signInRes.getExpiration();
         expired = Instant.parse(getConfigs("expirationDate")).isBefore(new Date().toInstant());
//         System.out.println("value is "+expired +new Date().toInstant().toString());
       return helper.getLatestResponse().getStatusCode();
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


    public static String getConfigs(String propName) {

        String filePath = "src/test/resources/Configs/configs.properties";
        File file = new File(filePath);
        FileReader reader = null;
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e1) {
            System.out.println("file not found");
            e1.printStackTrace();
        }
        Properties prop = new Properties();
        try {
            prop.load(reader);
        } catch (IOException e) {
            System.out.println("file not read");
            e.printStackTrace();
        }
        return prop.getProperty(propName);
    }
    public  static void setToken() throws IOException {
        String filePath = "src/test/resources/Configs/configs.properties";
        File file = new File(filePath);
        FileReader reader = null;
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e1) {
            System.out.println("file not found");
            e1.printStackTrace();
        }
        Properties prop = new Properties();
        try {
            prop.load(reader);
        } catch (IOException e) {
            System.out.println("file not read");
            e.printStackTrace();
        }
        prop.setProperty("Token",accessToken);
        FileOutputStream out = new FileOutputStream(filePath);
        prop.store(out, null);
        out.close();
        System.out.println(prop.getProperty("Token"));
    }
}
