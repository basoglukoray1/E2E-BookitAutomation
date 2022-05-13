package com.bookit.utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookitUtils {


    public static String generateToken(String email, String password) {

        Response response = given()
                .accept(ContentType.JSON)
                .queryParam("email", email)
                .and()
                .queryParam("password", password)
                .when().get(ConfigurationReader.get("base_url") + "/sign");

        String token = "Bearer " + response.path("accessToken");

        return token;
    }

    //hw
    //create method that accepts String like "teacher", "student-leader","student-member"
    // you will get one valid email and password for each from configuration.properties
    //and return valid token

    public static String generateTokenForEach(String user) {

        String token = "Bearer ";


        switch (user) {

            case "teacher":
                ConfigurationReader.get("teacher_email");
                ConfigurationReader.get("teacher_password");
                token += given().accept(ContentType.JSON).queryParam("email", ConfigurationReader.get("teacher_email"))
                        .queryParam("password", ConfigurationReader.get("teacher_password")).when().get(ConfigurationReader.get("base_url") + "/sign")
                        .then().log().all().extract().response().jsonPath().getString("accessToken");

                break;
            case "student_leader":
                ConfigurationReader.get("team_leader_email");
                ConfigurationReader.get("team_leader_password");
                token += given().accept(ContentType.JSON).queryParam("email", ConfigurationReader.get("team_leader_email"))
                        .queryParam("password", ConfigurationReader.get("team_leader_password")).when().get(ConfigurationReader.get("base_url") + "/sign")
                        .then().log().all().extract().response().jsonPath().getString("accessToken");
                break;
            case "student_member_1":
                ConfigurationReader.get("team_member1_email");
                ConfigurationReader.get("team_member_password");
                token += given().accept(ContentType.JSON).queryParam("email", ConfigurationReader.get("team_member1_email"))
                        .queryParam("password", ConfigurationReader.get("team_member1_password")).when().get(ConfigurationReader.get("base_url") + "/sign")
                        .then().log().all().extract().response().jsonPath().getString("accessToken");
                break;
            case "student_member_2":
                ConfigurationReader.get("team_member2_email");
                ConfigurationReader.get("team_member_password");
                token += given().accept(ContentType.JSON).queryParam("email", ConfigurationReader.get("team_member2_email"))
                        .queryParam("password", ConfigurationReader.get("team_member2_password")).when().get(ConfigurationReader.get("base_url") + "/sign")
                        .then().log().all().extract().response().jsonPath().getString("accessToken");
                break;
            case "student_member_3":
                ConfigurationReader.get("team_member3_email");
                ConfigurationReader.get("team_member3_password");
                token += given().accept(ContentType.JSON).queryParam("email", ConfigurationReader.get("team_member3_email"))
                        .queryParam("password", ConfigurationReader.get("team_member3_password")).when().get(ConfigurationReader.get("base_url") + "/sign")
                        .then().log().all().extract().response().jsonPath().getString("accessToken");
                break;
            case "student_member_4":
                ConfigurationReader.get("team_member4_email");
                ConfigurationReader.get("team_member_password");
                token += given().accept(ContentType.JSON).queryParam("email", ConfigurationReader.get("team_member4_email"))
                        .queryParam("password", ConfigurationReader.get("team_member4_password")).when().get(ConfigurationReader.get("base_url") + "/sign")
                        .then().log().all().extract().response().jsonPath().getString("accessToken");
                break;

        }

        return token;
    }
}