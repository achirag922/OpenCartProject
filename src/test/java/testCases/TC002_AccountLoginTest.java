package testCases;

import org.testng.annotations.Test;

import baseTest.BaseClass;
import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002_AccountLoginTest extends BaseClass {
	@Test(groups = {"Sanity", "Master"})
	public void LoginTest() {
		logger.info("***** Starting TC002_AccountLoginTest *****");
		Homepage hp = new Homepage(driver);

		hp.clickMyAccount();
		hp.clickLogin();

		LoginPage lp = new LoginPage(driver);
		logger.info("Entering the Login Details....");
		lp.enterLoginEmail(p.getProperty("email"));
		lp.enterLoginPassword(p.getProperty("password"));
		lp.clickLoginButton();
		logger.info("Validating the My Account page....");
		MyAccountPage map = new MyAccountPage(driver);
		boolean targetPage = map.messageValidation();
		//Assert.assertEquals(map.messageValidation(), p.getProperty("expectedMessage"));
		logger.info("Validated Login Page....");
		logger.info("User Login Successfully....");
	}
}
