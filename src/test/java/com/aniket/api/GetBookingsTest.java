package com.aniket.api;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class GetBookingsTest extends BaseApiTest {

    @Test
    void returnsListOfBookings() {
        given()
            .spec(spec)
        .when()
            .get("/booking")
        .then()
            .statusCode(200);
    }

    @Test
    void returnsJson() {
        given()
            .spec(spec)
        .when()
            .get("/booking")
        .then()
            .statusCode(200)
            .contentType("application/json; charset=utf-8");
    }
}