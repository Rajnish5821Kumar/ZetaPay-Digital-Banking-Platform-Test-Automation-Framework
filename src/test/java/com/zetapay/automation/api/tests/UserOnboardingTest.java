package com.zetapay.automation.api.tests;

import com.github.javafaker.Faker;
import com.zetapay.automation.api.BaseTest;
import com.zetapay.automation.api.dtos.UserDTO;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class UserOnboardingTest extends BaseTest {

    private Faker faker = new Faker();
    private int userId;

    @Test(priority = 1)
    public void testUserOnboarding() {
        UserDTO user = UserDTO.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .phoneNumber(faker.phoneNumber().cellPhone())
                .dateOfBirth("1990-01-01")
                .address(faker.address().fullAddress())
                .panNumber("ABCDE1234F")
                .build();

        userId = given()
                .spec(requestSpec)
                .body(user)
        .when()
                .post("/users")
        .then()
                .statusCode(201)
                .body("id", notNullValue())
                .extract().path("id");
        
        System.out.println("Created User ID: " + userId);
    }

    @Test(priority = 2, dependsOnMethods = "testUserOnboarding")
    public void testGetUser() {
        given()
                .spec(requestSpec)
        .when()
                .get("/users/" + userId)
        .then()
                .statusCode(200)
                .body("email", notNullValue());
    }
}
