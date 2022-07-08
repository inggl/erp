package com.github.inggl.erp.notification.rest;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestHTTPEndpoint(NotificationResource.class)
class NotificationResourceTest {

    @Test
    void testCountEndpoint()
    {
        given()
                .when().get("/stream/count")
                .then().statusCode(200);
    }
}