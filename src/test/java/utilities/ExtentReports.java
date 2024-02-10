package utilities;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

		public class ExtentReports implements ITestListener {

		    private ExtentReports extent;
		    private ExtentTest test;

		    @BeforeClass
		    public void onStart(ITestContext context) {
		        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent-report.html");
		        extent = new ExtentReports();
		        extent.attachReporter(htmlReporter);
		        
		        // Create a test with environment details
		        test = extent.createTest("Test Environment Details");
		        test.info("OS: Windows 10");
		        test.info("Java Version: 1.8.0_201");
		        test.info("Host Name: LTPPUN052363123");
		        test.info("Browser: Chrome");
		    }

		    @AfterClass
		    public void onFinish(ITestContext context) {
		        extent.flush();
		    }

		    @BeforeMethod
		    public void onTestStart(ITestResult result) {
		        // Start a new test
		        test = extent.createTest(result.getMethod().getMethodName());
		    }

		    @AfterMethod
		    public void onTestSuccess(ITestResult result) {
		        // Log test success status
		        test.log(Status.PASS, "Test passed");
		    }

		    @AfterMethod
		    public void onTestFailure(ITestResult result) {
		        // Log test failure status
		        test.log(Status.FAIL, "Test failed");
		    }

		    @AfterMethod
		    public void onTestSkipped(ITestResult result) {
		        // Log test skipped status
		        test.log(Status.SKIP, "Test skipped");
		    }

		    @Test
		    public void testExample() {
		        // Your test code here
		        System.out.println("This is a TestNG test.");
		    }
		}

	
		