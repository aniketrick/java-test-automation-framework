package com.aniket.api;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class GetBookingsTest {

    private static final String BASE_URL = "https://restful-booker.herokuapp.com";

    @Test
    void returnsListOfBookings() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/booking")
        .then()
            .statusCode(200);
    }
}