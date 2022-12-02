package api_Actions_And_Assertion;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class ApiAssertions {

    public static void assertStatusCodeIs_200(Response response)
    {
        ((ValidatableResponse)response).assertThat().statusCode(200);
    }

    public static void assertStatusCodeIs_404(Response response)
    {
        ((ValidatableResponse)response).assertThat().statusCode(404);
    }

    public static void assertStatusCodeIs_401_NotAuthorized(Response response)
    { ((ValidatableResponse)response).assertThat().statusCode(401); }

    public static void assertJsonContentType(Response response)
    {((ValidatableResponse)response).assertThat().contentType(ContentType.JSON); }

    public static void assertXMLContentType(Response response)
    { ((ValidatableResponse)response).assertThat().contentType(ContentType.XML); }

    public static void checkResponse_NotNull(Response response) {
        assert ((ValidatableResponse)response) != null;
    }
}
