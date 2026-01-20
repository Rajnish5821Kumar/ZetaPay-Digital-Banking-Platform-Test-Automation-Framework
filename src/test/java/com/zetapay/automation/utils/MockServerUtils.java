package com.zetapay.automation.utils;

import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class MockServerUtils {

    private static WireMockServer wireMockServer;

    public static void startServer() {
        if (wireMockServer == null) {
            wireMockServer = new WireMockServer(8080);
            wireMockServer.start();
            setupStubs();
        }
    }

    public static void stopServer() {
        if (wireMockServer != null) {
            wireMockServer.stop();
            wireMockServer = null;
        }
    }

    private static void setupStubs() {
        // Stub for User Onboarding (POST /users)
        wireMockServer.stubFor(post(urlEqualTo("/v1/users"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{ \"id\": 12345, \"message\": \"User created successfully\" }")));

        // Stub for Get User (GET /users/12345)
        wireMockServer.stubFor(get(urlPathMatching("/v1/users/.*"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{ \"id\": 12345, \"firstName\": \"John\", \"lastName\": \"Doe\", \"email\": \"john.doe@example.com\" }")));

        // Stub for UI (GET /)
        wireMockServer.stubFor(get(urlEqualTo("/"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/html")
                        .withBody("<html><body><h1>Login</h1><input id='username'><input id='password'><button id='loginBtn'>Login</button></body></html>")));
    }
}
