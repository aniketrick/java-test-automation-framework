package com.aniket.api;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class HealthCheckTest extends BaseApiTest {

    @Test
    void apiIsUp() {
        given()
            .spec(spec)
        .when()
            .get("/ping")
        .then()
            .statusCode(201);
    }
}