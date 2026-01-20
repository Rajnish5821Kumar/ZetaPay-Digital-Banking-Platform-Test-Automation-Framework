package com.zetapay.automation.api;

import com.zetapay.automation.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import com.zetapay.automation.utils.MockServerUtils;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    protected RequestSpecification requestSpec;

    @BeforeSuite
    public void beforeSuite() {
        MockServerUtils.startServer();
    }

    @AfterSuite
    public void afterSuite() {
        MockServerUtils.stopServer();
    }

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ConfigReader.get("base.url");
        RestAssured.basePath = ConfigReader.get("api.version");

        requestSpec = new RequestSpecBuilder()
                .setContentType("application/json")
                .setBaseUri(RestAssured.baseURI)
                .setBasePath(RestAssured.basePath)
                .build();
    }
}
