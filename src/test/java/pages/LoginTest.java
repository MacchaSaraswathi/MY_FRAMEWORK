package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
	
	private WebDriver driver;

	@BeforeMethod
	public void setUp() {
		// Set up the WebDriver instance
		System.setProperty("webdriver.gecko.driver", "drivers/windows/geckodriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Test
	public void testLogin() {
		// Open the website
		driver.get("https://demowebshop.tricentis.com/");
		
		// Click on the Login link
		driver.findElement(By.linkText("Log in")).click();

		// Initialize the LoginPage
		LoginPage loginPage = new LoginPage(driver);
		// Perform login
		loginPage.login("macchasaraswathi1999@gmail.com", "macchasaraswathi");

	}

	@AfterMethod
	public void tearDown() {
		// Close the browser
		//driver.quit();
	}

}





