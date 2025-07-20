package com.zephyr.framework;

import io.restassured.response.Response;

public class TestResultPoster {

    private static final String JIRA_PROJECT_KEY = "AUT";

    private static final String TEST_CYCLE_KEY = "AUT-R1";

    public static void postResult(String testCaseKey, String status, String comment, String screenshotPath) {
        // Basic escaping to prevent errors if the comment contains quotes.
        String formattedComment = comment.replace("\"", "\\\"");

        String payload = String.format(
                "{\"projectKey\": \"%s\", \"testCaseKey\": \"%s\", \"statusName\": \"%s\", \"testCycleKey\": \"%s\", \"comment\": {\"text\": \"%s\"}}",
                JIRA_PROJECT_KEY, testCaseKey, status, TEST_CYCLE_KEY, formattedComment
        );

        System.out.println("Sending payload to Zephyr Scale: " + payload);
        Response response = ZephyrApiClient.createTestExecution(payload);

        if (response != null && response.getStatusCode() == 201) {
            System.out.printf("Zephyr Scale Updated: %s → %s%n", testCaseKey, status);
        } else {
            System.err.println("--- FAILED TO UPDATE ZEPHYR SCALE ---");
            System.err.printf("Test Case: %s%n", testCaseKey);
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