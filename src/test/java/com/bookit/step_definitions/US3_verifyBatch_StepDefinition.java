package com.bookit.step_definitions;

import com.bookit.pages.SelfPage;
import com.bookit.utilities.BookitUtils;
import com.bookit.utilities.BrowserUtils;
import com.bookit.utilities.ConfigurationReader;
import com.bookit.utilities.DBUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class US3_verifyBatch_StepDefinition {

    SelfPage selfPage = new SelfPage();
    Response response;
    String token;
    String globalEmail;

    String batchNameUI;
    Integer batchNameAPI;
    Integer batchNameDB;


    @Given("I get batch name from my self page")
    public void i_get_batch_name_from_my_self_page() {

        // UI
        BrowserUtils.waitFor(5);
        batchNameUI = selfPage.batch.getText();


    }

    @And("I logged in Bookit API using {string}")
    public void iLoggedInBookitAPIUsing(String user) {

        token = BookitUtils.generateTokenForEach(user);
        globalEmail = BookitUtils.getEmail(user);
    }

    @When("I GET the current leader batch name from API")
    public void i_get_the_current_leader_batch_name_from_api() {

        response = given().accept(ContentType.JSON)
                .and().header("Authorization", token)
                .when().get(ConfigurationReader.get("base_url") + "/api/batches/my");
        response.prettyPrint();
    }

    @When("I get the batch name from Database")
    public void i_get_the_batch_name_from_database() {

        //DB
        String query = "select b.number from users u inner join team on u.team_id = team.id inner join batch b on b.number = batch_number where firstname ='Lissie'";
        Map<String, Object> dbMap1 = DBUtils.getRowMap(query);


        batchNameDB = (int) dbMap1.get("number");
    }

    @Then("UI,API and Database user information must match")
    public void ui_api_and_database_user_in() {


        //API
        JsonPath jsonPath = response.jsonPath();

        Integer batchNameAPI = jsonPath.getInt("number");


        //compare database vs API
        Assert.assertEquals(batchNameDB, batchNameAPI);


        //UI
        String actualBatchUI = selfPage.batch.getText();

        //compare UI vs API
        Assert.assertEquals("#" + batchNameAPI, actualBatchUI);


    }


}
