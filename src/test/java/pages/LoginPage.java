package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	private WebDriver driver;

	private By emailInput = By.id("macchasaraswathi1999@gmail.com");
	private By passwordInput = By.id("macchasaraswathi");
	private By loginButton = By.className("login-button");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void setEmail(String email) {
		driver.findElement(emailInput).sendKeys(email);
	}

	public void setPassword(String password) {
		driver.findElement(passwordInput).sendKeys(password);
	}

	public void clickLoginButton() {
		driver.findElement(loginButton).click();
	}

	public void login(String email, String password) {
		setEmail(email);
		setPassword(password);
		clickLoginButton();

}
}
