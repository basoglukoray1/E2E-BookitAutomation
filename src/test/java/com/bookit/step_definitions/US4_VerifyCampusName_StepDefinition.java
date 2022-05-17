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

import static io.restassured.RestAssured.given;

public class US4_VerifyCampusName_StepDefinition {

    SelfPage selfPage=new SelfPage();
    Response response;
    String token;
    String globalEmail;

    String campusLocationUI;
    String campusLocationAPI;
    String campusLocationDB;



    @Given("I GET campus location from my self page")
    public void i_get_campus_location_from_my_self_page() {

        BrowserUtils.waitFor(5);
        campusLocationUI=selfPage.campus.getText();
        System.out.println(campusLocationUI+ "UI");
    }

    @And("I logged Bookit API using with student {string}")
    public void i_Logged_Bookit_API_Using_With_Student(String user) {

        token= BookitUtils.generateTokenForEach(user);
        globalEmail=BookitUtils.getEmail(user);

    }




    @When("I GET the current student campus location from API")
    public void i_get_the_current_student_campus_location_from_api() {

        response=given().accept(ContentType.JSON)
                .and().header("Authorization",token)
                .when().get(ConfigurationReader.get("base_url")+"/api/campuses/my");
        campusLocationAPI=response.path("location");

        System.out.println(campusLocationAPI+"API");

    }
    @When("I GET the campus location from Database")
    public void i_get_the_campus_location_from_database() {


        String query2="select c.location from users u inner join team t on u.team_id = t.id inner join campus c on t.campus_id = c.id where u.email ='"+globalEmail+"'";
        campusLocationDB= (String) DBUtils.getCellValue(query2);

        System.out.println(campusLocationDB+"DB");
    }


    @Then("UI,API and Database user campus location must match")
    public void uiAPIAndDatabaseUserCampusLocationMustMatch() {


        Assert.assertEquals(campusLocationDB,campusLocationAPI,campusLocationUI);

    }
}
