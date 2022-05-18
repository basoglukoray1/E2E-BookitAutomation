package com.bookit.step_definitions;
import com.bookit.pages.SelfPage;
import com.bookit.pages.SignInPage;
import com.bookit.utilities.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class pair3_step_Defs {

    SelfPage selfPage = new SelfPage();
    Response response;
    String token;
    String globalEmail;
    String batchNameUI;
    Integer batchNameAPI;
    Integer batchNameDB;

    @Given("user  logs in using {string} {string}")
    public void user_logs_in_using(String email, String password) {
        Driver.get().get(ConfigurationReader.get("url"));
        Driver.get().manage().window().maximize();
        SignInPage signInPage = new SignInPage();
        signInPage.email.sendKeys(email);
        signInPage.password.sendKeys(password);
        BrowserUtils.waitFor(1);
        signInPage.signInButton.click();
    }
    @Given("user is  on the my self page")
    public void user_is_on_the_my_self_page() {
        selfPage = new SelfPage();
        selfPage.goToSelf();
    }
    @Given("I get  team name from my self page")
    public void i_get_team_name_from_my_self_page() {
        BrowserUtils.waitFor(5);
        batchNameUI = selfPage.batch.getText();
    }
    @Given("I logged  Bookit API using {string}")
    public void i_logged_bookit_api_using(String user) {
        token = BookitUtils.generateTokenForEach(user);
        globalEmail = BookitUtils.getEmail(user);
    }
    @When("I GET  the current student team name from API")
    public void i_get_the_current_student_team_name_from_api() {
        response = given().accept(ContentType.JSON)
                .and().header("Authorization", token)
                .when().get(ConfigurationReader.get("base_url") + "/api/batches/my");
        response.prettyPrint();
    }
    @When("I get the  team name from Database")
    public void i_get_the_team_name_from_database() {

        String query = "select b.number from users u inner join team on u.team_id = team.id inner join batch b on b.number = batch_number where firstname ='Cedi'";
        Map<String, Object> dbMap1 = DBUtils.getRowMap(query);


        batchNameDB = (int) dbMap1.get("number");
    }
    @Then("UI,API  and Database user information must match")
    public void ui_api_and_database_user_information_must_match() {

        JsonPath jsonPath = response.jsonPath();

        Integer batchNameAPI = jsonPath.getInt("number");


        Assert.assertEquals(batchNameDB, batchNameAPI);


        String actualBatchUI = selfPage.batch.getText();

        Assert.assertEquals("#" + batchNameAPI, actualBatchUI);
    }



}