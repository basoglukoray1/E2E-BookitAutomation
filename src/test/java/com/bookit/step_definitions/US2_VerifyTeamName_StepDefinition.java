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
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class US2_VerifyTeamName_StepDefinition {


    SelfPage selfPage=new SelfPage();
    Response response;
    String token;
    String globalEmail;

    String teamNameUI;
    String teamNameAPI;
    String teamNameDB;



    @Given("I get team name from my self page")
    public void i_get_team_name_from_my_self_page() {
        BrowserUtils.waitFor(5);
        teamNameUI=selfPage.team.getText();



    }

    @Given("I logged Bookit API using {string}")
    public void i_logged_Bookit_API_using(String user) {

       token= BookitUtils.generateTokenForEach(user);
      globalEmail=BookitUtils.getEmail(user);



    }

    @When("I GET the current student team name from API")
    public void i_GET_the_current_student_team_name_from_API() {

        response=given().accept(ContentType.JSON)
                .and().header("Authorization",token)
                .when().get(ConfigurationReader.get("base_url")+"/api/teams/my");
        teamNameAPI=response.path("name");



    }

    @And("I get the team name from Database")
    public void iGetTheTeamNameFromDatabase() {


        String query="select t.name from users u inner join team t on u.team_id = t.id where u.email ='"+globalEmail+"'";
        teamNameDB= (String) DBUtils.getCellValue(query);




    }


    @Then("UI,API and Database user information must match")
    public void ui_API_and_Database_user_information_must_match() {

        Assert.assertEquals(teamNameDB,teamNameAPI,teamNameUI);

    }


}
