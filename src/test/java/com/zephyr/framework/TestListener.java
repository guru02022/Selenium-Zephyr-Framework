package com.zephyr.framework;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestListener extends TestListenerAdapter {

    // TODO: Set your Project Key and Test Cycle Key here.
    private static final String JIRA_PROJECT_KEY = "CXOS";
    private static final String TEST_CYCLE_KEY = "CXOS-R20";

    @Override
    public void onTestSuccess(ITestResult result) {
        handleResult(result, "Pass");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Object testInstance = result.getInstance();
        if (testInstance instanceof HasWebDriver) {
            WebDriver driver = ((HasWebDriver) testInstance).getDriver();
            ScreenshotUtil.captureScreenshot(driver, result.getName()); // <-- Capture screenshot here
        }
        handleResult(result, "Fail");
    }

    private void handleResult(ITestResult result, String status) {
        String testCaseKey = getTestCaseKey(result);
        if (testCaseKey != null) {
            TestResultPoster.postResult(JIRA_PROJECT_KEY, TEST_CYCLE_KEY, testCaseKey, status);
        }
    }

    private String getTestCaseKey(ITestResult result) {
        String description = result.getMethod().getDescription();
        if (description != null) {
            Pattern pattern = Pattern.compile("([A-Z0-9]+-T\\d+)");
            Matcher matcher = pattern.matcher(description);
            if (matcher.find()) {
                return matcher.group(1).trim();
            }
        }
        System.err.println("Jira Test Case Key not found in description for test: " + result.getName());
        return null;
    }
}