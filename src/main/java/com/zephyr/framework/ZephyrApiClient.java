package com.zephyr.framework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ZephyrApiClient {

    private static final String ZEPHYR_API_URL = "https://api.zephyrscale.smartbear.com/v2";

    private static final String ZEPHYR_API_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjb250ZXh0Ijp7ImJhc2VVcmwiOiJodHRwczovL2d1cnVrdWxsYXlhcHBhMDg4LTE3NTI3MzE1MTAzMzYuYXRsYXNzaWFuLm5ldCIsInVzZXIiOnsiYWNjb3VudElkIjoiNzEyMDIwOmI5ZDMzZDEyLTEyNWItNDQxOS04Y2Q4LTE4NTVhMzZhMWYxMyIsInRva2VuSWQiOiI4ZTY3ZTFjNi01YzI5LTQwZGYtYTQwNC1lY2MyNTI4MDU3ZWQifX0sImlzcyI6ImNvbS5rYW5vYWgudGVzdC1tYW5hZ2VyIiwic3ViIjoiYmM2NGRhZDYtODVhMC0zZjUxLTkwODEtYzFjYjdjZTE2ZDFkIiwiZXhwIjoxNzg0MzUzMDE5LCJpYXQiOjE3NTI4MTcwMTl9.RQhZhMRASm07m3QxecCc3MTrE6a14sEhGQSKHVYZAF4";

    public static Response createTestExecution(String payload) {
        try {
            return RestAssured.given()
                    .header("Authorization", "Bearer " + ZEPHYR_API_TOKEN)
                    .contentType(ContentType.JSON)
                    .body(payload)
                    .when()
                    .post(ZEPHYR_API_URL + "/testexecutions");
        } catch (Exception e) {
            System.err.println("Zephyr API call failed: " + e.getMessage());
            return null;
        }
    }
}