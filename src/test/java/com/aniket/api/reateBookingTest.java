package com.aniket.api;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

class CreateBookingTest {

    private static final String BASE_URL = "https://restful-booker.herokuapp.com";

    @Test
    void createsBookingAndReturnsSameDetails() {
        String newBooking = """
            {
              "firstname": "Aniket",
              "lastname": "Tester",
              "totalprice": 150,
              "depositpaid": true,
              "bookingdates": {
                "checkin": "2027-01-10",
                "checkout": "2027-01-12"
              },
              "additionalneeds": "Breakfast"
            }
            """;

        given()
            .baseUri(BASE_URL)
            .contentType("application/json")
            .accept("application/json")
            .body(newBooking)
        .when()
            .post("/booking")
        .then()
            .statusCode(200)
            .body("bookingid", notNullValue())
            .body("booking.firstname", equalTo("Aniket"))
            .body("booking.lastname", equalTo("Tester"))
            .body("booking.totalprice", equalTo(150))
            .body("booking.depositpaid", equalTo(true))
            .body("booking.bookingdates.checkin", equalTo("2027-01-10"));
    }
}