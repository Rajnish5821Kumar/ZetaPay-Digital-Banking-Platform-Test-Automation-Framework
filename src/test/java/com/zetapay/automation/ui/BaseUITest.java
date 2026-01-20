package com.zetapay.automation.ui;

import com.zetapay.automation.utils.ConfigReader;
import com.zetapay.automation.utils.MockServerUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseUITest {

    @BeforeMethod
    public void setup() {
        MockServerUtils.startServer();
        DriverManager.initDriver();
        DriverManager.getDriver().get(ConfigReader.get("base.url")); // Or admin portal URL
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
        MockServerUtils.stopServer();
    }
}
