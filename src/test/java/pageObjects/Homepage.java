package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage extends BasePage {

	public Homepage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//span[contains(text(),'My Account')]")
	WebElement myAccount_btn;

	@FindBy(xpath = "//a[contains(text(),'Register')]")
	WebElement register_btn;

	@FindBy(xpath = "//a[normalize-space()='Login']")
	WebElement login_btn;

	public void clickMyAccount() {
		myAccount_btn.click();
	}

	public void clickRegister() {
		register_btn.click();
	}

	public void clickLogin() {
		login_btn.click();
	}

}
