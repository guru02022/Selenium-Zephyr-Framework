package com.zephyr.framework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ZephyrApiClient {

    private static final String ZEPHYR_API_URL = "https://api.zephyrscale.smartbear.com/v2";

    // TODO: Generate and paste your Zephyr Scale API Token here.
    private static final String ZEPHYR_API_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjb250ZXh0Ijp7ImJhc2VVcmwiOiJodHRwczovL2d1cnUxMjM0NS5hdGxhc3NpYW4ubmV0IiwidXNlciI6eyJhY2NvdW50SWQiOiI3MTIwMjA6YjlkMzNkMTItMTI1Yi00NDE5LThjZDgtMTg1NWEzNmExZjEzIiwidG9rZW5JZCI6IjAyYWI1ZGE1LWFiOGEtNGU3Mi04NDU2LWRiYzFiMzVlMmUyOSJ9fSwiaXNzIjoiY29tLmthbm9haC50ZXN0LW1hbmFnZXIiLCJzdWIiOiIwYzdhNmQ4ZS1kMTQzLTM5Y2EtYjQyNC1lZDA5MWJhMTE0NGIiLCJleHAiOjE3ODQ1NDQ5OTEsImlhdCI6MTc1MzAwODk5MX0.JtgBCO6LNm8Q5ckul2_PJGHxvCbpurneuJXJA1hoZh8";

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