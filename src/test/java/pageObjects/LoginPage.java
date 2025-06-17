package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement email;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement password;

	@FindBy(xpath = "//input[@value='Login']")
	WebElement login_btn;

	public void enterLoginEmail(String e) {
		email.sendKeys(e);
	}

	public void enterLoginPassword(String pass) {
		password.sendKeys(pass);
	}

	public void clickLoginButton() {
		login_btn.click();
	}

}
