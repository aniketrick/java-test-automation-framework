package com.aniket.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

abstract class BaseApiTest {

    protected static RequestSpecification spec;

    @BeforeAll
    static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "https://restful-booker.herokuapp.com");

        spec = new RequestSpecBuilder()
            .setBaseUri(baseUrl)
            .setContentType("application/json")
            .setAccept("application/json")
            .build();
    }
}