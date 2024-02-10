package utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.internal.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MyListener implements ITestListener {
	static WebDriver driver;
    static WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        // step1: formulate a test domain url & driver path
        String siteUrl = "https://www.facebook.com";
        String driverPath = "drivers/windows/chromedriver.exe";

        // step2: set system properties for selenium dirver
        System.setProperty("webdriver.chrome.driver", driverPath);

        // step3: instantiate selenium webdriver
        driver = new ChromeDriver();

        // step4: add explicit wait (Conditional Delay)
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        // step5: launch browser
        driver.get(siteUrl);
    }

    @Test
    public void testFailureLogin() {
        driver.findElement(By.id("email")).sendKeys("siri@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("abc@123");
        driver.findElement(By.name("login")).submit();

        takeScreenShot("facebook-login.png");

        // evaluate a failure login test
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#loginform > div:nth-child(12) > div._9ay7")));

        String errorText = "The password that you've entered is incorrect. Forgotten password?";

        if (errorText.equals(errorMsg.getText())) {
            System.out.println("Test is Passed !");
        } else {
            System.out.println("Test is Failed !");
        }

        System.out.println("Expected Error Text : " + errorText);
        System.out.println("Actual Error Text : " + errorMsg.getText());
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void takeScreenShot(String fileName) {
        // 1. type cast driver instance too take screen shot
        TakesScreenshot tsc = (TakesScreenshot) driver ;

        // 2. call take screen shot method with file type
        File src = tsc.getScreenshotAs(OutputType.FILE);

        // 3. create  file  with screen shot
        try {
            FileHandler.copy(src, new File("C:\\Users\\Thilak\\Downloads\\MethodFailureScreenShots\\output-screenshot.jpg" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

	
	

	    

	  