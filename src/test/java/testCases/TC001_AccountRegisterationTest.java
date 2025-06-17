package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseClass;
import pageObjects.AccountRegisterationPage;
import pageObjects.Homepage;

public class TC001_AccountRegisterationTest extends BaseClass {

	@Test(groups = {"Regression", "Master"})
	public void RegisterTest() {
		try {
			logger.info("***** Starting TC001_AccountRegisterationTest *****");

			String Password = randomAlphaNumeric();
			Homepage hp = new Homepage(driver);
			AccountRegisterationPage regPage = new AccountRegisterationPage(driver);

			logger.info("User is in the home page....");
			hp.clickMyAccount();
			hp.clickRegister();
			logger.info("Entering the account details....");
			regPage.enterFirstName(randomString().toUpperCase());
			regPage.enterLastName(randomString().toUpperCase());
			regPage.enterEmail(randomString() + "@gamil.com");
			regPage.enterTelephone(randomNumbers());
			regPage.enterPassword(Password);
			regPage.enterCnfPassword(Password);
			regPage.enableCheckbox();
			regPage.clickContinue();
			logger.info("Validating the Account....");
			Assert.assertEquals(regPage.getConfimationMessage(), "Your Account Has Been Created!");
			logger.info("Account Registered....");
			logger.info("Validated Expected Message....");
		} catch (Exception e) {
			logger.error("Account Registeraion Failed");
			logger.debug("Debug Error....");
			Assert.fail();
		}
	}

}
