package com.github.inggl.erp.notification.rest;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestHTTPEndpoint(ArchiveResource.class)
class ArchiveResourceTest {
    @Test
    void testFindAllEndpoint()
    {
        given()
                .when().get()
                .then().statusCode(200);
    }
}