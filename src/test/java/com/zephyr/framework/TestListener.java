package com.zephyr.framework;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestListener extends TestListenerAdapter {

    @Override
    public void onTestSuccess(ITestResult result) {
        handleResult(result, "Pass", "Test executed successfully.", null);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String screenshotPath = null;
        Object testClass = result.getInstance();
        if (testClass instanceof HasWebDriver) {
            WebDriver driver = ((HasWebDriver) testClass).getDriver();
            if (driver != null) {
                screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());
            }
        }
        handleResult(result, "Fail", "Test failed. Error: " + result.getThrowable().getMessage(), screenshotPath);
    }

    private void handleResult(ITestResult result, String status, String comment, String screenshotPath) {
        String testCaseKey = getTestCaseKey(result);
        if (testCaseKey != null) {
            TestResultPoster.postResult(testCaseKey, status, comment, screenshotPath);
        }
    }

    private String getTestCaseKey(ITestResult result) {
        String description = result.getMethod().getDescription();
        if (description != null) {
            // Updated regex to find keys like AUT-T1, AUT-T-1
            Pattern pattern = Pattern.compile("([A-Z]+-T-?\\d+)");
            Matcher matcher = pattern.matcher(description);
            if (matcher.find()) {
                return matcher.group(1);
            }
        }
        System.err.println("Jira Test Case Key not found in description for test: " + result.getName());
        return null;
    }
}