package com.zephyr.framework;

import io.restassured.response.Response;

public class TestResultPoster {

    public static void postResult(String projectKey, String testCycleKey, String testCaseKey, String status) {

        String payload = String.format(
                "{\"projectKey\": \"%s\", \"testCaseKey\": \"%s\", \"statusName\": \"%s\", \"testCycleKey\": \"%s\"}",
                projectKey, testCaseKey, status, testCycleKey
        );

        System.out.println("Sending payload to Zephyr Scale: " + payload);
        Response response = ZephyrApiClient.createTestExecution(payload);

        if (response != null && response.getStatusCode() == 201) {
            System.out.printf("Zephyr Scale Updated: %s → %s%n", testCaseKey, status);
        } else {
            System.err.println("--- FAILED TO UPDATE ZEPHYR SCALE ---");
            if (response != null) {
                System.err.printf("Status Code: %d%n", response.getStatusCode());
                System.err.printf("Response Body: %s%n", response.getBody().asString());
            } else {
                System.err.println("Response was null. Check ZephyrApiClient for exceptions.");
            }
            System.err.println("------------------------------------");
        }
    }
}
