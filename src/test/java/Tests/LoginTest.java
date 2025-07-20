package Tests;

import com.zephyr.framework.HasWebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class LoginTest implements HasWebDriver {

    private WebDriver driver;

    @Override
    public WebDriver getDriver() {
        return this.driver;
    }

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(); // Add ChromeOptions for headless if needed
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test(description = "Verify successful login. Jira Key: AUT-T1")
    public void testSuccessfulLogin() {
        driver.get("https://practicetestautomation.com/practice-test-login/");
        driver.findElement(By.id("username")).sendKeys("student");
        driver.findElement(By.id("password")).sendKeys("Password123");
        driver.findElement(By.id("submit")).click();

        Assert.assertEquals(driver.getCurrentUrl(),
                "https://practicetestautomation.com/logged-in-successfully/",
                "URL after login is incorrect.");

        WebElement successMessage = driver.findElement(By.tagName("strong"));
        Assert.assertTrue(successMessage.getText().contains("Congratulations"));
    }

    @Test(description = "Verify successful login. Jira Key: AUT-T2")
    public void testFailedLogin() {
        driver.get("https://practicetestautomation.com/practice-test-login/");
        driver.findElement(By.id("username")).sendKeys("incorrectUser");
        driver.findElement(By.id("password")).sendKeys("incorrectPassword");
        driver.findElement(By.id("submit")).click();

        WebElement errorMessage = driver.findElement(By.id("error"));
        Assert.assertTrue(
                errorMessage.isDisplayed(), "Error message should be visible on wrong login.");    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
