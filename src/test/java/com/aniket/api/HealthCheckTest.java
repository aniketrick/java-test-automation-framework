package com.aniket.api;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class HealthCheckTest {

    private static final String BASE_URL = "https://restful-booker.herokuapp.com";

    @Test
    void apiIsUp() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/ping")
        .then()
            .statusCode(201);
    }
}