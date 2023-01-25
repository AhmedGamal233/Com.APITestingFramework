package stepDefinition;

import io.cucumber.java.en.Given;

import static stepDefinition.ApiBaseTest.authApis;
import static stepDefinition.ApiBaseTest.helper;

public class Login {

    @Given("user try to sign out")
    public void userTryToSignOut() {
        helper.setLatestResponse(authApis.signOut());
    }
}
