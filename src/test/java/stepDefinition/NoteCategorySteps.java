package stepDefinition;

import com.fasterxml.jackson.databind.DeserializationFeature;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import pojos.pojoResponses.UserContent.UserNoteCategories.UserNoteCategories;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static stepDefinition.ApiBaseTest.*;

public class NoteCategorySteps {
    public  UserNoteCategories userNoteCategories;
    @Given("user open note categories")
    public void userOpenNoteCategories(DataTable dataTable) {
        Map<String, Object> categoriesParameters =new HashMap<>();
        categoriesParameters.putAll(dataTable.asMap(String.class, Object.class));
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Response response= userNoteCategoriesRequests.getNoteCategories(categoriesParameters);
        helper.setLatestResponse(response);

    }
    @SneakyThrows
    @When("note categories has items")
    public void noteCategoriesHasItems() {
        UserNoteCategories userNoteCategories =objectMapper.readValue(helper.getLatestResponse().prettyPrint(), UserNoteCategories.class);
        userNoteCategories.getItems().forEach(item -> System.out.println(item.getNoteCategoryId()));
    }

    @Then("check each item values")
    public void checkEachItemValues() {
        userNoteCategories.getItems().forEach(item -> assertThat(item.getNoteCategoryId(), notNullValue()));
        //assert each object in item json object with same line
    }
}
