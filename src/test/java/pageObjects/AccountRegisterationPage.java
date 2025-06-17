package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegisterationPage extends BasePage {
	public AccountRegisterationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "input-firstname")
	WebElement firstname;

	@FindBy(id = "input-lastname")
	WebElement lastname;

	@FindBy(id = "input-email")
	WebElement email;

	@FindBy(id = "input-telephone")
	WebElement telephone;

	@FindBy(id = "input-password")
	WebElement password;

	@FindBy(id = "input-confirm")
	WebElement confirm_password;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement agree_checkbox;

	@FindBy(xpath = "//input[@value='Continue']")
	WebElement continue_btn;

	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement confirmation_message;

	public void enterFirstName(String fname) {
		firstname.sendKeys(fname);
	}

	public void enterLastName(String lname) {
		lastname.sendKeys(lname);
	}

	public void enterEmail(String Email) {
		email.sendKeys(Email);
	}

	public void enterTelephone(String tele) {
		telephone.sendKeys(tele);
	}

	public void enterPassword(String pass) {
		password.sendKeys(pass);
	}

	public void enterCnfPassword(String cnfPass) {
		confirm_password.sendKeys(cnfPass);
	}

	public void enableCheckbox() {
		agree_checkbox.click();
	}

	public void clickContinue() {
		continue_btn.click();
	}

	public String getConfimationMessage() {
		try {
			return confirmation_message.getText();
		} catch (Exception e) {
			return e.getMessage();
		}

	}

}
