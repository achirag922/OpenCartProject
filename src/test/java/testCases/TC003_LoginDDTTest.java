package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseClass;
import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class TC003_LoginDDTTest extends BaseClass {
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = {"DataDriven"})
	public void verify_LoginDDT(String email, String password, String exp) {

		logger.info("***** Starting TC003_LoginDDTTest *****");
		Homepage hp = new Homepage(driver);

		hp.clickMyAccount();
		hp.clickLogin();

		LoginPage lp = new LoginPage(driver);
		logger.info("Entering the Login Details....");
		lp.enterLoginEmail(email);
		lp.enterLoginPassword(password);
		lp.clickLoginButton();
		logger.info("Validating the My Account page....");
		MyAccountPage map = new MyAccountPage(driver);
		// Assert.assertEquals(map.messageValidation(),
		// p.getProperty("expectedMessage"));
		boolean targetPage = map.messageValidation();
		logger.info("Validated Login Page....");
		logger.info("User Login Successfully....");

		if (exp.equalsIgnoreCase("Valid")) {
			if (targetPage == true) {
				map.clickLogout();
				Assert.assertTrue(true);

			} else {
				Assert.assertTrue(false);
			}
		}
		if (exp.equalsIgnoreCase("Invalid")) {
			if (targetPage == true) {
				map.clickLogout();
				Assert.assertTrue(false);
			} else {
				Assert.assertTrue(true);
			}
		}
		logger.info("***** Finished TC003_LoginDDTTest *****");
	}

}
