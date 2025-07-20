package com.zephyr.framework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ZephyrApiClient {

    private static final String ZEPHYR_API_URL = "https://api.zephyrscale.smartbear.com/v2";

    // TODO: Generate and paste your Zephyr Scale API Token here.
    private static final String ZEPHYR_API_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjb250ZXh0Ijp7ImJhc2VVcmwiOiJodHRwczovL2xpYmVydHl3aXJlbGVzcy5hdGxhc3NpYW4ubmV0IiwidXNlciI6eyJhY2NvdW50SWQiOiI3MTIwMjA6MGZjZjU4NDgtMDlmNS00ZGUyLThmMDQtYTlmOWUwYzQ2OTdhIiwidG9rZW5JZCI6IjM0MTYyOThlLTEyMTEtNDI2My1hMTI5LTVhMzJiMTU4MDRlYSJ9fSwiaXNzIjoiY29tLmthbm9haC50ZXN0LW1hbmFnZXIiLCJzdWIiOiJqaXJhOmYxMmJkYWNlLTVmOTUtNGZlOS1iNDZjLWZiNGNmNWMxMzA5NSIsImV4cCI6MTc4NDU1MjI3MywiaWF0IjoxNzUzMDE2MjczfQ.mFYoxb1tqvsb2T8J2blG3KxfoQZzk1iMXEzEEVl6URo";

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