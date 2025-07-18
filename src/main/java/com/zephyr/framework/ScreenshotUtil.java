package com.zephyr.framework;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String testName) {
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyy_MM_dd__HH_mm_ss").format(new Date());
            String screenshotName = testName + "_" + timestamp + ".png";
            String screenshotPath = "screenshots/" + screenshotName;

            Files.createDirectories(Paths.get("screenshots"));
            Files.copy(scrFile.toPath(), Paths.get(screenshotPath));

            System.out.println("Screenshot saved to: " + screenshotPath);
            return screenshotPath;
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
            return null;
        }
    }
}