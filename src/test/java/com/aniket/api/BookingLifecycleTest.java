package com.aniket.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

class BookingLifecycleTest extends BaseApiTest {

    private String token;
    private int bookingId;

    @BeforeEach
    void logInAndCreateBooking() {
        token = given()
            .spec(spec)
            .body("""
                { "username": "admin", "password": "password123" }
                """)
        .when()
            .post("/auth")
        .then()
            .statusCode(200)
            .extract().path("token");

        bookingId = given()
            .spec(spec)
            .body(bookingJson("Aniket", 150))
        .when()
            .post("/booking")
        .then()
            .statusCode(200)
            .extract().path("bookingid");
    }

    @Test
    void updatesBooking() {
        given()
            .spec(spec)
            .cookie("token", token)
            .body(bookingJson("Updated", 300))
        .when()
            .put("/booking/" + bookingId)
        .then()
            .statusCode(200)
            .body("firstname", equalTo("Updated"))
            .body("totalprice", equalTo(300));
    }

    @Test
    void deletesBooking() {
        given()
            .spec(spec)
            .cookie("token", token)
        .when()
            .delete("/booking/" + bookingId)
        .then()
            .statusCode(201);

        given()
            .spec(spec)
        .when()
            .get("/booking/" + bookingId)
        .then()
            .statusCode(404);
    }

    @Test
    void refusesUpdateWithoutToken() {
        given()
            .spec(spec)
            .body(bookingJson("Hacker", 1))
        .when()
            .put("/booking/" + bookingId)
        .then()
            .statusCode(403);
    }

    private String bookingJson(String firstName, int price) {
        return """
            {
              "firstname": "%s",
              "lastname": "Tester",
              "totalprice": %d,
              "depositpaid": true,
              "bookingdates": { "checkin": "2027-01-10", "checkout": "2027-01-12" },
              "additionalneeds": "Breakfast"
            }
            """.formatted(firstName, price);
    }
}