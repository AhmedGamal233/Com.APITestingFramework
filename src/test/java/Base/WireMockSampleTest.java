package Base;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;


public class WireMockSampleTest {
    private static WireMockServer wireMockServer;
    //private static final String EVENTS_PATH = "/odds";
    private static final String EVENTS_PATH = "/api/users/2";
    private static final String APPLICATION_JSON = "application/json";

    @BeforeClass
    public static void before() throws Exception {
        System.out.println("Setting up!");
        final int port = 8089;
        wireMockServer = new WireMockServer(new WireMockConfiguration().port(8080));

        wireMockServer.start();
    //   configureFor("localhost", wireMockServer.port());
        RestAssured.port = port;
//        stubFor(get(urlEqualTo(EVENTS_PATH)).willReturn(
//                aResponse().withStatus(200)
//                        .withHeader("Content-Type", APPLICATION_JSON)
//                        .withBody("ODDS")));
//        stubFor(post(urlEqualTo("/odds/new"))
//                .withRequestBody(containing("{\"price\":5.25,\"status\":1,\"ck\":13.1,\"name\":\"X\"}"))
//                .willReturn(aResponse().withStatus(201)));

        wireMockServer.stubFor(get(urlPathEqualTo(EVENTS_PATH)).willReturn(
                        aResponse().withHeader("ContentType", String.valueOf(ContentType.JSON)).withStatus(200)
                                .withBody("{\"data\":{\"id\":2,\"name\":\"fuchsia rose\",\"year\":2001,\"color\":\"#C74375\",\"pantone_value\":\"17-2031\"},\"support\":{\"url\":\"https://reqres.in/#support-heading\",\"text\":\"To keep ReqRes free, contributions towards server costs are appreciated!\"}}")));
    }


    @Test
    public void getAssert() {

        String url = "http://localhost:8080";

       String s=  given().
                when().header("ContentType", ContentType.JSON).get(url+EVENTS_PATH).then().assertThat().statusCode(200).log().body().toString();
       System.out.println(s);
      //  verify(getRequestedFor(urlEqualTo("http://localhost:8080"+EVENTS_PATH)));




    }


    @AfterClass
    public static void after() throws Exception {
        System.out.println("Running: tearDown");
        wireMockServer.stop();
    }

}
